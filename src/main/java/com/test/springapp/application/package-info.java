/**
 * Application Layer - Orchestrates use cases and coordinates domain objects.
 * <p>
 * This layer:
 * <ul>
 *   <li>Defines application-specific business rules</li>
 *   <li>Orchestrates the flow of data to and from domain entities</li>
 *   <li>Directs domain objects to work out problems</li>
 *   <li>Keeps thin - no business logic, only coordination</li>
 * </ul>
 * <p>
 * Contains:
 * <ul>
 *   <li><b>Application Services</b> - Implement use cases, transaction boundaries</li>
 *   <li><b>DTOs</b> - Data transfer objects for communication between layers</li>
 *   <li><b>Commands</b> - Represent write operations</li>
 *   <li><b>Queries</b> - Represent read operations</li>
 *   <li><b>Mappers</b> - Convert between domain models and DTOs</li>
 * </ul>
 *
 * @see com.test.springapp.application.service Application services
 * @see com.test.springapp.application.dto Data Transfer Objects
 */
package com.test.springapp.application;
