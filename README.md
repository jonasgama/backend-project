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