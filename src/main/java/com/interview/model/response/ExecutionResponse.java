package com.interview.model.response;

import com.interview.exceptions.result.Result;
import com.interview.exceptions.result.Results;

public class ExecutionResponse {

    private final Results results;
    private Object successBody;

    private ExecutionResponse(Results results) {
        this.results = results;
    }

    public static ExecutionResponse buildErrorExecutionResponse(Results results) {
        return new ExecutionResponse(results);
    }

    public static ExecutionResponse buildSuccessfulExecutionResponse () {
        return new ExecutionResponse(Results.results(Result.OK));
    }

    public static ExecutionResponse buildSuccessfulExecutionResponse(Object successBody) {
        ExecutionResponse executionResponse = buildSuccessfulExecutionResponse();
        executionResponse.setSuccessBody(successBody);
        return executionResponse;
    }

    public boolean isOK() {
        return results.isOK();
    }

    public Results getResults() {
        return results;
    }

    public Object getSuccessBody() {
        return successBody;
    }

    public void setSuccessBody(Object successBody) {
        this.successBody = successBody;
    }

}