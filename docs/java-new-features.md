## Primitive classes
> Java Valhalla
> https://openjdk.org/jeps/401


## Scoped Value
> 替代 ThreadLocal 的存在, 不可变.
> https://vividcode.cc/java-20-scoped-value/

Scoped Value 的一个重要出发点是与虚拟线程一同使用，适用于 thread-per-request (**每个请求一个线程**) 的并发模式。在处理请求时，之前的代码可以指定 ScopedValue 的值，之后运行的代码则使用该值。