package com.test.springapp.application.query;

/**
 * Interface for query handlers.
 * Query handlers execute queries and return results.
 * 
 * @param <Q> the query type
 * @param <R> the result type
 */
public interface QueryHandler<Q extends Query, R> {
    
    /**
     * Handles the given query.
     * @param query the query to handle
     * @return the result
     */
    R handle(Q query);
}
