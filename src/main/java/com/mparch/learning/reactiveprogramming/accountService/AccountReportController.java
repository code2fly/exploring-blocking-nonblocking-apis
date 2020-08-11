package com.mparch.learning.reactiveprogramming.accountService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class AccountReportController {

    private final Delayer delayer;

    public AccountReportController(Delayer delayer) {
        this.delayer = delayer;
    }

    @GetMapping("/account")
    public CompletableFuture<String> getAccountDataForCustomer(@RequestParam("custId") String customerId) {
        log.info("First thread in account api {}", Thread.currentThread());

        if (delayer.getDelay() > 0) {
            try {
                System.out.println("introducing custom delay while getting account data ");
                Thread.sleep(delayer.getDelay());
            } catch (InterruptedException e) {
                System.out.println("error while introducing custom delay");
            }
        }

        log.info("Last thread in account api {}", Thread.currentThread());
        return CompletableFuture.completedFuture("account report for customer " + customerId);
    }

}
