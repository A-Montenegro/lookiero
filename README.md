User Management
========================

Currently in Lookiero, we need to implement a service for customer registration. From business they have given us the following use cases:

# Use cases

## Add User

We need to be able to add user to our system. We need to retrieve those information of the user:
* username
* password
* birthdate
* height
* weight

The user domain invariants are:
* username: at least 8 nonblank alphanumeric and unique
* age: greater or equal 18 years
* password: nonblank
* heigh: in m, between 1,00 and 2,30
* weight: in kg, between 40 and 150

## Update user
We need be able to modify user data:
* height
* weight

## Retrieve users
We need to be able to retrieve the registered user list ordered by birthdate.

## Create Statistics
For statistics purpose, we need to maintain a relationship between the year of birth and the average of the users BMI (Body Mass Index):

```math
BMI = weight / (height)^2
```

So, on every register / update we need to calculate and store the BMI of the year.

## Retrieve Statistics
Wee need to be able to retrieve the statistics orderer by BMI


# Details
Our system will be used with different clients, CLI and REST.

## CLI
A terminal through CLI (Command Line Interface).
It could be helpful having a `help` parameter.

## REST
a REST Client.
It could be helpful having a Swagger documentation.

# What we are looking for

* Usage of Java 17 with Gradle
* SpringBoot in infrastructure layer
* Don't use any framework in the domain code, we want to see you code.
* Pay attention about how your code is organized.
* How you are reflecting the domain in the code.
* We love clean code.
* We don't think 100% of code coverage is a must, but we love tests.
* We are looking forward to seeing your code and discuss with you your solution.
