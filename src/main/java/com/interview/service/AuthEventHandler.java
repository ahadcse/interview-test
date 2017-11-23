package com.interview.service;

import com.interview.model.Auth.AuthenticationAttempt;
import com.interview.store.AuthenticationAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

public class AuthEventHandler implements AuthenticationEventPublisher {

    @Autowired
    private AuthenticationAttemptRepository authenticationAttemptRepository;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();
        authenticationAttemptRepository.save(new AuthenticationAttempt(username, AuthenticationAttempt.AuthAttemptResult.SUCCESS));
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        authenticationAttemptRepository.save(new AuthenticationAttempt(username, AuthenticationAttempt.AuthAttemptResult.FAILURE));
    }
}
