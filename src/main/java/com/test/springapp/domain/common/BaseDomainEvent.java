package com.test.springapp.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

/**
 * Base implementation for domain events.
 */
@Getter
@EqualsAndHashCode
public abstract class BaseDomainEvent implements DomainEvent {
    
    private final UUID eventId;
    private final Instant occurredOn;
    
    protected BaseDomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
    }
}
