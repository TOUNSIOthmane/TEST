/**
 * Presentation Layer - Handles interaction with external actors (users, systems).
 * <p>
 * This layer:
 * <ul>
 *   <li>Handles HTTP requests and responses</li>
 *   <li>Validates input</li>
 *   <li>Translates domain exceptions to HTTP status codes</li>
 *   <li>Formats output for clients</li>
 *   <li>Should be thin - delegates to application layer</li>
 * </ul>
 * <p>
 * Contains:
 * <ul>
 *   <li><b>REST Controllers</b> - Handle HTTP endpoints</li>
 *   <li><b>Exception Handlers</b> - Translate exceptions to HTTP responses</li>
 *   <li><b>Request/Response Models</b> - API-specific models</li>
 *   <li><b>Validators</b> - Input validation</li>
 * </ul>
 *
 * @see com.test.springapp.presentation.rest REST controllers
 * @see com.test.springapp.presentation.exception Exception handlers
 */
package com.test.springapp.presentation;
