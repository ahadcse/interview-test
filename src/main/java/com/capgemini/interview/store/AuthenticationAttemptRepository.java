package com.capgemini.interview.store;


import com.capgemini.interview.model.Auth.AuthenticationAttempt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AuthenticationAttemptRepository extends CrudRepository<AuthenticationAttempt, Long> {
    public List<AuthenticationAttempt> findByUsername(String username);
    public List<AuthenticationAttempt> findByUsernameAndAuthAttemptResult(String username, AuthenticationAttempt.AuthAttemptResult result);
    public Page<AuthenticationAttempt> findByUsernameAndAuthAttemptResult(String username, AuthenticationAttempt.AuthAttemptResult result, Pageable pageable);
    public List<AuthenticationAttempt> deleteByUsername(String username);
}
