/**
 * Package is used to produce services and configuration to run the application and use cases.
 *
 * <p>
 * <b>Application Service</b> acts as a facade through which clients will interact with the domain model. Application services have the following
 * characteristics:
 * <ul>
 *     <li>They are stateless</li>
 *     <li>They enforce system security</li>
 *     <li>They control the database transactions</li>
 *     <li>They orchestrate business operations but do not make any business decisions (i.e., they do not contain any business logic)</li>
 * </ul>
 */
package com.hexagonal.demo.application;