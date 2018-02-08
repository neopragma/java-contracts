# Java Contracts

A simple facility to support Design by Contract in Java.

## What is Design by Contract?

There's a nice explanation [on the Eiffel website](https://www.eiffel.com/values/design-by-contract/introduction/), so there's no need to reiterate it here. 

Most implementations of libraries to support contracts appear to treat them as a separate concern from the application logic. Implementations often try to separate the contract functionality from the application source in some way. 

In Java implementations, this may involve pre-processing custom javadoc comments, using aspects, or using annotations. 

These problems may stem from a common misconception that Design by Contract is a testing technique. This has led people to build in functionality to enable and disable the contract-processing logic.

In fact, the intent is to enforce contracts in production. Implementations that go to great lengths to abstract the contract handling code from the application code only succeed in adding arbitrary complexity to the code base, the test suite, and the build configuration. It's no wonder that people have stopped using pretty much all of the implementations that have come and gone over the years. 

In the author's view, contracts are properly part of an application's basic functionality. It is appropriate for the contract assertions to be in plain sight in the application code, at the beginning and/or end of methods. 

Having struggled to get value from implementations that are difficult to configure and sensitive to version conflicts in transitive dependencies, I have a preference for keeping it simple. This implementation is little more than a thin wrapper around simple conditional statements. 

The value lies in the fact that the source code becomes expressive of intent in a way that naked "if" statements are not. 

Another potential source of value comes from a psychological or cultural factor. When something doesn't have a name, it's difficult for people to reason about it. It's difficult for them even to remember to consider it. 

A good deal of production software in the wild is wide open to errors and hacking because programmers typically don't define and enforce contracts. When contracts are a "thing" that has a name and whose syntax is visually distinct from generic conditional statements, it may be easier for programmers to remember to do this.

A statement like 

```java
if (capacity < 1) throw new IllegalArgumentException();

```

is understandable, but a statement like 

```java
Contract.require(capacity < 1, "Capacity must be greater than zero");

```

causes the reader to think in terms of domain concepts and language rather than generic implementation concepts and language.