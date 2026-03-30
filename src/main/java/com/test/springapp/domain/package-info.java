/**
 * Domain Layer - The core of the application containing business logic.
 * <p>
 * This layer is the heart of the software and should be:
 * <ul>
 *   <li>Independent of frameworks and external libraries</li>
 *   <li>Testable without any infrastructure</li>
 *   <li>Focused on business rules and logic</li>
 * </ul>
 * <p>
 * The domain layer contains:
 * <ul>
 *   <li><b>Entities</b> - Objects with unique identity and lifecycle</li>
 *   <li><b>Aggregates</b> - Clusters of entities treated as a unit</li>
 *   <li><b>Value Objects</b> - Immutable objects defined by their attributes</li>
 *   <li><b>Domain Services</b> - Stateless operations that don't fit in entities</li>
 *   <li><b>Repository Interfaces</b> - Contracts for persistence (implemented in infrastructure)</li>
 *   <li><b>Domain Events</b> - Notifications of important domain occurrences</li>
 * </ul>
 *
 * @see com.test.springapp.domain.common Base interfaces and classes
 * @see com.test.springapp.domain.model Domain entities and aggregates
 */
package com.test.springapp.domain;
