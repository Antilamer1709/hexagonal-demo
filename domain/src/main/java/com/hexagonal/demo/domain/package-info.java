/**
 * Package is used to produce core of the application. It consists of two main parts:
 * <ul>
 *     <li>Model it is used to define domain models</li>
 *     <li>Port it is used to define primary and secondary ports</li>
 * </ul>
 * <p>
 * <b>Ports</b> represent the boundaries of the application. Frequently they are implemented as interfaces to be used by
 * outside parties. Their implementation resides outside the application, although they share the same domain.
 * <p>
 * <b>Primary ports</b> are also known as driving ports. These are the first communication points between the outside
 * and
 * the application core. Therefore, they can still be otherwise known as Inbound Ports. These ports “drive” the
 * application. This means that this is where requests get through to the application. The upstream in this case
 * contains data and the downstream contains the response to that request. Primary ports reside on the left side of
 * the hexagon.
 * <p>
 * <b>Secondary ports</b> are in contrast known as driven ports. These also live on the outside and symmetrically to the
 * primary ports they live on the right side of the hexagon. The application core uses secondary ports to upstream
 * data to external entities. For example, an operation that needs data from the database will use a secondary ports.
 * The application “drives” the port in order to get data. The downstream contains thus the data coming from external
 * entities on the right. Because the application sends data to the outside, secondary ports also get called Outbound
 * Ports.
 */
package com.hexagonal.demo.domain;