package com.test.springapp.shared.exception;

/**
 * Exception thrown when business rules are violated.
 */
public class BusinessRuleViolationException extends DomainException {
    
    public BusinessRuleViolationException(String message) {
        super(message);
    }
    
    public BusinessRuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
