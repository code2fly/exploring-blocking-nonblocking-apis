package com.mparch.learning.reactiveprogramming.creditService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCheckReportController {

    private Delayer delayer;

    public CreditCheckReportController(Delayer delayer) {
        this.delayer = delayer;
    }

    @GetMapping("/dps")
    public String getCreditReportForCustomer(@RequestParam("custid") String customerId) {
        if (delayer.getDelay() > 0) {
            try {
                System.out.println("introducing custom delay while getting credit data ");
                Thread.sleep(delayer.getDelay());
            } catch (InterruptedException e) {
                System.out.println("error while introducing custom delay");
            }
        }
        return "credit check report for customer : " + customerId;
    }
}
