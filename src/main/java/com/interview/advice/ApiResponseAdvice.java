package com.interview.advice;

import com.interview.exceptions.result.Result;
import com.interview.exceptions.result.Results;
import com.interview.model.response.ApiResponse;
import com.interview.exceptions.DatabaseErrorException;
import com.interview.model.response.ExecutionResponse;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.Map;
import java.util.TreeMap;

import static com.interview.exceptions.result.Results.results;
import static org.slf4j.LoggerFactory.getLogger;

@ControllerAdvice
public class ApiResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    private static final Logger LOGGER = getLogger(ApiResponseAdvice.class);

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue mappingJacksonValue,
                                           MediaType mediaType,
                                           MethodParameter methodParameter,
                                           ServerHttpRequest serverHttpRequest,
                                           ServerHttpResponse serverHttpResponse) {
        Object value = mappingJacksonValue.getValue();
        if(value != null && value instanceof ApiResponse) {
            return;
        }
        if(value instanceof Map && Error.isError((Map) value)) {
            Error error = Error.fromMap((Map) value);
            setBody(mappingJacksonValue, value, error.getStatus(), error.getMessage());
            return;
        }
        setBody(mappingJacksonValue, value, HttpStatus.OK.value(), "SUCCESSFUL");
    }

    @ExceptionHandler(value = DatabaseErrorException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ApiResponse<Object> databaseErrorHandler() {
        ExecutionResponse executionResponse = ExecutionResponse.buildErrorExecutionResponse(Results.results(Result.DB_ERROR));
        return apiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Database Error!", executionResponse.getResults());
    }

    private ApiResponse<Object> apiResponse(int statusCode, String responseMessage, Object value) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setResponseCode(statusCode);
        apiResponse.setResponseMessage(responseMessage);
        apiResponse.setResponseBody(value);
        return apiResponse;
    }

    private void setBody(MappingJacksonValue mappingJacksonValue, Object value, int status, String message) {
        ApiResponse<Object> apiResponse = apiResponse(status, message, value);
        mappingJacksonValue.setValue(apiResponse);
    }

    private static class Error {
        private int status;
        private String message;

        public int getStatus() {
            return status;
        }

        void setStatus(int status) {
            this.status = status;
        }

        String getMessage() {
            return message;
        }

        void setMessage(String message) {
            this.message = message;
        }

        static boolean isError(Map map) {
            Map<String, Object> copy = new TreeMap<>();
            map.forEach( (key, value) -> {copy.put(String.valueOf(key), value); });
            return copy.containsKey("exception") && map.containsKey("message") && map.containsKey("status");
        }

        static Error fromMap(Map map) {
            Error error = new Error();
            error.setStatus((Integer) map.get("status"));
            error.setMessage((String) map.get("message"));
            return error;
        }
    }
}
