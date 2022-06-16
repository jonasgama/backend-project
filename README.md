# NinjaOne Backend Interview Project

The project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`

## Starting the Application

Run the `BackendInterviewProjectApplication` class

Go to:
* http://localhost:8080/sample/1
* http://localhost:8080/sample/2

You should see results for both of these. The application is working and connected to the H2 database. 

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has the Sample Repository in it.

Type:

```sql
SELECT * FROM SAMPLE;
````

Click `Run`, you should see two rows, for ids `1` and `2`

### Suggestions

Feel free to remove or repurpose the existing Sample Repository, Entity, Controller, and Service. 


### Proposed design
I've created 3 domains
catalog -> everything about devices/services
customer -> everything related to account (what do the customer have acquired)
order -> purchase and transactions too.


### How was it tested?

By an integrated test class: BackendInterviewProjectApplicationTest.
This class covers the main use case of the challenge

along with unit tests. cover a variety of cases.

### Caveats:
test coverage wasn't my purpose 


### how to run it.
- load spring.
- order stuff:
- filter


### curl used to order devices and services
curl --location --request POST 'http://localhost:8080/orders/devices' \
--header 'Content-Type: application/json' \
--data-raw '{
"customer_id": "A",
"items": [
{
"device_id": "WINDOWS",
"services": [
"ANTIVIRUS-FOR-WINDOWS",
"BACKUP",
"SCREEN-SHARE"
]
},
{
"device_id": "WINDOWS",
"services": [
"ANTIVIRUS-FOR-WINDOWS",
"BACKUP",
"SCREEN-SHARE"
]
},
{
"device_id": "MAC",
"services": [
"ANTIVIRUS-FOR-MAC",
"BACKUP",
"SCREEN-SHARE"
]
},
{
"device_id": "MAC",
"services": [
"ANTIVIRUS-FOR-MAC",
"BACKUP",
"SCREEN-SHARE"
]
},
{
"device_id": "MAC",
"services": [
"ANTIVIRUS-FOR-MAC",
"BACKUP",
"SCREEN-SHARE"
]
}
]
}'

### curl used to filter

curl --location --request GET 'http://localhost:8080/orders/transactions/A?month=6&year=2022'