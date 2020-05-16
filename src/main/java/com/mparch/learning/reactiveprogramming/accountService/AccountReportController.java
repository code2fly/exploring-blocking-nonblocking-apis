package com.mparch.learning.reactiveprogramming.accountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountReportController {

    private final Delayer delayer;

    public AccountReportController(Delayer delayer) {
        this.delayer = delayer;
    }

    @GetMapping("/account")
    public String getAccountDataForCustomer(@RequestParam("custId") String customerId) {
        if (delayer.getDelay() > 0) {
            try {
                System.out.println("introducing custom delay while getting account data ");
                Thread.sleep(delayer.getDelay());
            } catch (InterruptedException e) {
                System.out.println("error while introducing custom delay");
            }
        }
        return "account report for customer " + customerId;
    }

}
