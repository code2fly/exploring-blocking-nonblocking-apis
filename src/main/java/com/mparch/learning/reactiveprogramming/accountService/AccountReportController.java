package com.mparch.learning.reactiveprogramming.accountService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountReportController {

    private final Delayer delayer;

    public AccountReportController(Delayer delayer) {
        this.delayer = delayer;
    }

    @GetMapping("/account")
    public ResponseEntity<String> getAccountDataForCustomer(@RequestParam("custId") String customerId) {
        log.info("First thread while receiving request in AccountService is :  {} ", Thread.currentThread());
        if (delayer.getDelay() > 0) {
            try {
                System.out.println("introducing custom delay while getting account data ");
                Thread.sleep(delayer.getDelay());
            } catch (InterruptedException e) {
                System.out.println("error while introducing custom delay");
            }
        }

        log.info("Thread used just before return of Account service request : {}", Thread.currentThread());
        return new ResponseEntity<>("account report for customer " + customerId, HttpStatus.OK);
    }

}
