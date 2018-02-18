
## Overview
Implementation revolves around the Order class and the getOrderTotal method.

#### Notes
* Switched to BigDecimal for precision/accuracy for monetary calculations. If performance is of concern,
an alternative implementation with ints could be evaluated.
* Alternative RoundingModes for BigDecimal could be used if Business Requirements call for it.
* Opted for Composition approach, but an Inheritance approach could also be evaluated if it would be a better
fit for the larger application.

## Unit Tests
Run all tests using ````mvn test````