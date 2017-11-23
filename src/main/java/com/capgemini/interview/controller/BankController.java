package com.capgemini.interview.controller;

import com.capgemini.interview.exceptions.AccountCreationException;
import com.capgemini.interview.exceptions.BankAccountException;
import com.capgemini.interview.exceptions.DepositException;
import com.capgemini.interview.exceptions.WithdrawalException;
import com.capgemini.interview.exceptions.result.Result;
import com.capgemini.interview.exceptions.result.Results;
import com.capgemini.interview.model.response.ApiResponse;
import com.capgemini.interview.model.response.BankResponse;
import com.capgemini.interview.service.BankAccountHandlerImpl;
import com.capgemini.interview.service.IBankAccountHandler;
import com.capgemini.interview.model.response.ExecutionResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    private static Logger LOGGER = LoggerFactory.getLogger(BankController.class);

    @ApiOperation(value = "Create Account")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse create(@RequestParam(value = "accountName", required = true) String accountName) {
        ApiResponse<ExecutionResponse> apiResponse = null;
        ExecutionResponse executionResponse = null;

        LOGGER.info("create controller started...");

        IBankAccountHandler bankAccountHandler = new BankAccountHandlerImpl();
        BankResponse bankAccount = bankAccountHandler.createBankAccount(accountName);
        if (bankAccount != null) { //TODO: More details check
            LOGGER.info("Account created " + bankAccount.toString());
            executionResponse = ExecutionResponse.buildSuccessfulExecutionResponse(bankAccount);
            apiResponse = new ApiResponse<>(200, "Account created");
            apiResponse.setResponseBody(executionResponse);
        }
        if (!executionResponse.isOK()) {
            throw new AccountCreationException("Cannot creation failed", Results.results(Result.ACCOUNT_CREATION_ERROR));
        }
        return apiResponse;
    }

    @ApiOperation(value = "Withdraw Money")
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ApiResponse withdraw(@RequestParam(value = "bankAccountId", required = true) int bankAccountId,
                                @RequestParam(value = "amount", required = true) float amount) {
        ApiResponse<ExecutionResponse> apiResponse = null;
        ExecutionResponse executionResponse = null;

        LOGGER.info("withdraw controller started...");

        IBankAccountHandler bankAccountHandler = new BankAccountHandlerImpl();
        BankResponse withdrawal = bankAccountHandler.withdrawal(bankAccountId, amount);
        if (withdrawal != null) {
            LOGGER.info("Withdraw successful " + withdrawal.toString());
            executionResponse = ExecutionResponse.buildSuccessfulExecutionResponse(withdrawal);
            apiResponse = new ApiResponse<>(200, "Withdraw successful");
            apiResponse.setResponseBody(executionResponse);
        }
        if (!executionResponse.isOK()) {
            throw new WithdrawalException("Withdraw failed", Results.results(Result.WITHDRAW_ERROR));
        }
        return apiResponse;
    }

    @ApiOperation(value = "Deposit Money")
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public ApiResponse<ExecutionResponse> deposit(@RequestParam(value = "bankAccountId", required = true) int bankAccountId,
                                @RequestParam(value = "amount", required = true) float amount) {
        ApiResponse<ExecutionResponse> apiResponse = null;
        ExecutionResponse executionResponse = null;

        LOGGER.info("deposit controller started...");

        IBankAccountHandler bankAccountHandler = new BankAccountHandlerImpl();
        BankResponse deposit = bankAccountHandler.deposit(bankAccountId, amount);
        if(deposit != null) {
            LOGGER.info("Deposit successful " + deposit.toString());
            executionResponse = ExecutionResponse.buildSuccessfulExecutionResponse(deposit);
            apiResponse = new ApiResponse<>(200, "Deposit successful");
            apiResponse.setResponseBody(executionResponse);
        }
        if(!executionResponse.isOK()) {
            throw new DepositException("Deposit failed", Results.results(Result.DEPOSIT_ERROR));
        }
        return apiResponse;
    }

    @ApiOperation(value = "Get Balance")
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ApiResponse balance(@RequestParam(value = "bankAccountId", required = true) int bankAccountId) {
        ApiResponse apiResponse;

        LOGGER.info("balance controller executed...");

        IBankAccountHandler bankAccountHandler = new BankAccountHandlerImpl();
        float balance = bankAccountHandler.getBalance(bankAccountId);
        apiResponse = new ApiResponse<>(200, "Successful");
        apiResponse.setResponseBody(balance);
        return apiResponse;
    }
}
