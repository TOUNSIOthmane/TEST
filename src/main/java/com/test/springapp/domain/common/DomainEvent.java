package com.test.springapp.domain.common;

import java.time.Instant;
import java.util.UUID;

/**
 * Base interface for all domain events.
 * Domain events represent something that happened in the domain that domain experts care about.
 */
public interface DomainEvent {
    
    /**
     * Returns the unique identifier of this event.
     * @return the event ID
     */
    UUID getEventId();
    
    /**
     * Returns the timestamp when this event occurred.
     * @return the occurrence timestamp
     */
    Instant getOccurredOn();
}
