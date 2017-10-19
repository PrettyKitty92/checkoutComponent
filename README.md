# basket-component

## Description

API that calculates the total price of a number of items.
Checkout mechanism can scan items and return actual price (is stateful)


## Interface Method
``` addItem(String name) ```
    add new Item into basket
    
``` deleteItem(String name) ```
    delete item form basket

``` checkoutTotalPrice() ```
    check total price of items in basket


``` clear() ```
    empty your basket

## Run test from command line using maven

```mvn clean verify```

