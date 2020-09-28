# Build and run
To build:
```
> mvn clean install
```

Running, as required:
```
> java –jar menu.jar menu.xml asc
> java –jar menu.jar menu.json desc
```

## Design thought/decisions
 * xjc to generate the parsing Objects for XML/Json.
    * need different objects for Json?
 * Those objects are pretty anemic, either transform to a richer model or wrap the classes (Menu class)
 * Menu should be able to sort and display itself.
 
## Improvements
* Dependency injection from the top down.
* Move comparators from enum so that we can add sorting on any field.
* Lombok all the things.
