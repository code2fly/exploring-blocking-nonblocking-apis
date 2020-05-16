package com.mparch.learning.reactiveprogramming.bankservice;

import com.mparch.learning.reactiveprogramming.accountService.AccountReportController;
import com.mparch.learning.reactiveprogramming.creditService.CreditCheckReportController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

@Slf4j
@RestController
public class MortgageController {

    private final AccountReportController accountService;

    private final CreditCheckReportController creditCheckService;

    public MortgageController(AccountReportController accountService, CreditCheckReportController creditCheckService) {
        this.accountService = accountService;
        this.creditCheckService = creditCheckService;
    }

    @GetMapping("/mortgage")
    public DeferredResult<MortgageReport> getMortgageReport(@RequestParam("custId") String customerId) throws Exception {
        log.info("First thread in mortgage api {}", Thread.currentThread());

        DeferredResult<MortgageReport> output = new DeferredResult<>();
        Callable<String> accountDataForCustomer = accountService.getAccountDataForCustomer(customerId);
        log.info("Second thread in mortgage  api after account call {}", Thread.currentThread());
        Callable<String> creditReportForCustomer = creditCheckService.getCreditReportForCustomer(customerId);
        log.info("Third thread in mortgage  api after credit api call {}", Thread.currentThread());

        ForkJoinPool.commonPool().submit(() ->
                output.setResult(new MortgageReport(accountDataForCustomer.call(), creditReportForCustomer.call()))
        );

        return output;
    }
}


class MortgageReport {

    private String accountDetails;
    private String creditCheckDetails;

    public MortgageReport(String accountDetails, String creditCheckDetails) {
        this.accountDetails = accountDetails;
        this.creditCheckDetails = creditCheckDetails;
    }

    public void setAccountDetails(String accountDetails) {
        this.accountDetails = accountDetails;
    }

    public void setCreditCheckDetails(String creditCheckDetails) {
        this.creditCheckDetails = creditCheckDetails;
    }

    public String getAccountDetails() {
        return accountDetails;
    }

    public String getCreditCheckDetails() {
        return creditCheckDetails;
    }
}