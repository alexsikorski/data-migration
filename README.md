# Data Migration

Importing a **CSV** to a **SQL database** with proper error checking and performance testing.

## About

Java 11 project completed during training; task assigned had been to successfully migrate data to a local MySQL server. This project uses parallel threading in order to reduce the total amount of time taken to migrate the data from a CSV file into a MySQL table.

## Tools Used

- JDK 11
- MySQL
- IntelliJ
- MySQL Workbench 8.0 CE
- JUnit 5
- Git

## How to Use

Create `login.properties` in resources folder, fill in the following information:
```
username="your_username"
password="your_password"
```
This will allow you to establish a connection with your local MySQL server. You will need to edit the URL in the `EmployeeDAO` class, `connectToDataBase()` method. The default database used  is:
```
String filePath = "jdbc:mysql://localhost:3306/myLocalDb";
```

## Topics Covered within Functionality

- OOP Principles
- SOLID Principles
- Unit Testing
- Performance Testing
- Defined Packages
- MVC Design Pattern
- MySQL Java Integration 
- HashMaps
- Iterations
- Parallel Threading

## Example Test Results

```
-- Performance testing for reading --
Reading Time: 0.1071244s.
-- Performance testing for insertions --
Single Thread: 20.319822s.
Multi Thread (5 + 1): 8.881763s.
Multi Thread (15 + 1): 4.818716s.
Multi Thread (50 + 1): 4.002546s.
Multi Thread (61): 3.4939237s.
Multi Thread (100 + 1): 2.332285s.
```

#### Why do most threads include an additional thread?

This algorithm essentially divides the hashmap into `x` batches. This means that unless `x` is a **factor** of the size *(entries count)*, there will always be a remainder of entries not covered. This algorithm generates threads for those batches so when there are some remainder entries, a final thread is created.

When the developer specifies that he/she wants **61 threads**, an additional thread is not generated because 61 is a factor of the size **9943**. This means batches are **evenly** distributed.

#### What happens to duplicate entries?

This algorithm always keeps the first HashMap entry when a duplicate is found, it is then placed in a separate `HashMap`. This `HashMap` currently has no functionality, but its size is used for testing purposes. Further development can for example, export this `HashMap` as a **CSV** or **text file** for administrative purposes.

###### Authored by Alex Sikorski
