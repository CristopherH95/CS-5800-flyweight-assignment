# CS-5800: Flyweight Assignment

This repository contains code simulating text editor capabilities, for the purpose of demonstrating the flyweight design pattern,
for an assignment in CS-5800 (Advanced Software Engineering). The `driver` package implements a basic program demonstrating 
the instantiation of the different classes implemented here, and the basic capabilities outlined in the assignment.
These capabilities are:

- The generation of text with multiple style attributes (e.g., font size, font color, font size)
- Shared instances of text style attributes, using the flyweight pattern
- Saving text data to a file
- Restoring text data from a file

An example of the driver program's output can be found in the `output.png` file.

## Build & Run

To build with Maven, simply navigate to the project root in the command line and run:

```shell
mvn package
```

Alternatively, IDEs such as IntelliJ should be able to build the source files using their standard build utilities.

Once built, the project can be run using the `driver` package:

```shell
java -cp ./target/flyweight-assignment-1.0-SNAPSHOT.jar driver.Main
```
