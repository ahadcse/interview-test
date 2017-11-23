package com.interview.exceptions.result;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Collections;

public class Results {

    Collection<Result> results;

    private Results(Collection<Result> results) {
        this.results = results;
    }

    public static Results results (Result result) {
        return new Results(Collections.singletonList(result));
    }

    public static Results results (Collection<Result> results) {
        return new Results(results);
    }

    public Collection<Result> getResults() {
        return results;
    }

    @JsonIgnore
    public int size() {
        return results.size();
    }

    @JsonIgnore
    public boolean isOK() {
        return results.stream().allMatch(Result::isOK);
    }

    public boolean contains(Result result) {
        return results.contains(result);
    }
}