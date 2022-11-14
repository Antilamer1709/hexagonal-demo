/**
 * Package is used to produce secondary and primary adapters. It is needed to connect application to external systems and/or infrastructure.
 * <p>
 * <b>Adapters</b> are essentially the implementation of ports. They are not supposed to be called directly in any point in code
 * <p>
 * <b>Primary adapters</b> are implementations of primary ports. These are completely independent of the application core. This presents one of the more clear
 * advantages of this architecture. By implementing a port on the outside, we have control over how the implementation is done. This means that we can freely
 * implement different forms of getting the data through to the application, without affecting the application itself. Just as ports primary adapters can also
 * be called driving adapters. Examples of this are REST services and GUI’s.
 * <p>
 * <b>Secondary adapters</b> are implementations of secondary ports. Just as primary adapters, these are also independent of the application core with the same
 * clear advantage. More often, we find that it’s in the secondary ports that lie the more difficult questions regarding the choice of technology. Frequently
 * there is always the question on how do we actually want to implement a persistence layer. It can be difficult to choose the right database, or a file system
 * or anything else. By using adapters, we can easily interchange adapters as we please. This means that regardless of the implementation, our application also
 * does not change. It will only know the operations it needs to call and has no idea of how they are implemented. In the same way as primary adapters,
 * secondary adapters are also referred as driven adapters.
 */
package com.hexagonal.demo.adapter;