# circuits-topolgy
A topology serialization/de-serialization between objects and JSON with in memory manipulation.

## Details

- [x] Api Implementation in java as it is strongly typed and OOP language.
- [x] Gradle build tool is used for building, creating docs, running tests.
- [x] Api level documentation (javadoc).
- [x] Api level testing.
- [x] Static code analysis with [deepsource](https://deepsource.io/gh/Mohamed-Ibrahim-01/circuits-topolgy) (no issues found).
- [x] Git verison control is used.

## Some Assumptions 

- All components in the topolgy has to have a well defined structure and any unexcepted components (not Resistor nor Nmos) won't be handled.
- All Id values has to unique any duplication in Ids may cause undefined behaviour.
- Max, Default value in Resistor and Nmos Components should fit in double size.
- Resistance & m(l) in resistor and Nmos are different types even they have the same fields.

## Units Tests Results 
![image](https://user-images.githubusercontent.com/60495252/169420509-968b36a2-8ed2-4b8a-8b1d-11407a2ec62c.png)
