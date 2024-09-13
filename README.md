# spring_boot_with_multiple_db
A sample spring boot application with two postgres database connections

## DB Initialization
Create two database
1) company_db
2) employee_db

Run a sql files present in the multi_db/sql folder.

## App Run
- Modify the DB details in the application.properties file.
- Application will run on port 8085
- Hit the endpoint http://localhost:8085/company-employee
  - It will return the data from both the database.