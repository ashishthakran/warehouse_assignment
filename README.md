## Intro
This assignment will be used as a discussion during a technical interview.
Time constraints are part of software development and even though we don't expect a perfect solution, imagine your code to be on its way to production.
If you have to make compromises, provide a README to briefly explain pros and cons of your approach, what considerations you would make for another iteration and in general what your
future colleagues might be interested in if they had to pick up your code.

The primary values for the code we look for are: simplicity, readability, maintainability, testability. It should be easy to scan the code, and rather quickly understand what it’s doing.
Pay attention to naming.

## The Task
The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock.
It should be possible to load articles into the software from a file, see the attached inventory.json.
The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity.
The products should also be loaded from a file, see the attached products.json.

The warehouse should have at least the following functionality;
* Get all products and quantity of each that is an available with the current inventory
* Remove(Sell) a product and update the inventory accordingly

## Application Modules

The complete functionality is being developed to keep in mind the maximum decoupling among the modules (parent packages).

| Module Name | Description |
| --- | --- |
| <b>core</b> | This package contains core models and exceptions module. The package will contain all the common logic which can be used by other packages/modules. |
| <b>dao</b> | This module contains all configuration and classes that will be responsible for database interaction (e.g. repository and entity classes) . |
| <b>service</b> | This module contains all configuration and classes that will be responsible for business logic (e.g. business validations and any other business logic). |
| <b>web</b> | This module contains all the endpoints of the application |

Some shortcuts or cases that are not handled:

1. Detailed exception handling and handle different types of HTTP related issues.
2. Hardcoded values which can be moved to some static constants.
3. Detailed Logging throughout the application to gather as much as information.
4. Smart file processing (e.g. partial file and large file processing). Currently, application will process whole file or will not process at all.
5. Application allows a maximum <b>512 MB</b> file size to upload.
6. Only JSON files allowed to process.
7. 100% code coverage using tests is not complete.

##### Request Flow

User's Request ➡ Endpoint/Web ➡ Service ➡ DAO ➡ Data Repository ➡ Storage (DB, In-Memory etc.)

## Install & Running

### Prerequisites
* JDK 1.8
* Maven 3.5.2

Maven and JDK 1.8 must be exported in user's PATH variable.

### Pull from git
```
$ git clone https://github.com/MarktplaatsShowAndTell/eCG_Aashish_Thakran.git
$ cd eCG_Aashish_Thakran
```

### Build & run

* Build
```
$ mvn clean package
```

* Run test
```
$ mvn test
```

* Run the application
```
$ cd target
$ java -jar wms-api.jar
```

#### Application Endpoints

* ##### Swagger UI
```
$ http://localhost:8080/swagger-ui.html
```

* ##### H2 Console (To check in-memory database)
```
$ http://localhost:8080/h2-console

JDBC Url - jdbc:h2:mem:wms
User - sa
Password -
```

* ##### Articles/Inventory File Upload
```
$ http://localhost:8080/api/articles/upload
```

* ##### Get Articles
```
$ http://localhost:8080/api/articles
```

* ##### Products File Upload
```
$ http://localhost:8080/api/products/upload
```

* ##### Get Products with current available inventory
```
$ http://localhost:8080/api/products
```