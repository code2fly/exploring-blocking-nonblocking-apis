package com.mparch.learning.reactiveprogramming.bankservice;

import com.mparch.learning.reactiveprogramming.accountService.AccountReportController;
import com.mparch.learning.reactiveprogramming.creditService.CreditCheckReportController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MortgageController {

    private final AccountReportController accountService;

    private final CreditCheckReportController creditCheckService;

    public MortgageController(AccountReportController accountService, CreditCheckReportController creditCheckService) {
        this.accountService = accountService;
        this.creditCheckService = creditCheckService;
    }

    @GetMapping("/mortgage")
    public MortgageReport getMortgageReport(@RequestParam("custId") String customerId) {

        String accountDataForCustomer = accountService.getAccountDataForCustomer(customerId);
        String creditReportForCustomer = creditCheckService.getCreditReportForCustomer(customerId);

        return new MortgageReport(accountDataForCustomer, creditReportForCustomer);
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