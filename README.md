# First time setup

## Database setup

1. Install Mysql
2. DDL script location <projectlocation>/econsult/DataModel/DataModel-V1.sql

## Project setup

1. MVN clean install

## Start server

1. Jetty server can be started as a standalone java application. The location of main class is : /econsult/webapp/src/main/java/com/econsult/main/ServerInitializer.java
2. The server is listening on port 8080

## Api 

### Create Doctor
* Service end point: ** http://localhost:8080/econsult/u/doctor/ **
* content-type: ** Content-Type: application/json **
* HTTP Method: ** POST **
* Payload 
...

{
"firstName": "Doc",
"lastName": "d",
"password": "c",
"role": {
"roleName": "DOCTOR"
}

...


### Create Patient
* Service end point: ** http://localhost:8080/econsult/u/patient/ **
* content-type: ** Content-Type: application/json **
* HTTP Method: ** POST **
* Payload 
...

{
"firstName": "Pat",
"lastName": "d",
"password": "c",
"role": {
"roleName": "PATIENT"
}

...


### Create Query
* Service end point: ** http://localhost:8080/econsult/q/query/ **
* content-type: ** Content-Type: application/json **
* HTTP Method: ** POST **
* Payload 
...

{
text:"hi doc wassup",
patient{
  id: "4"
}
}

...

### Create Post
* Service end point: ** http://localhost:8080/econsult/q/post/ **
* content-type: ** Content-Type: application/json **
* HTTP Method: ** POST **
* Payload 
...

{
"text": "I am good",
"user": {
  "id": 1
},

"query":{
  "queryId":"1"
},

"postBy":{
"roleName": "DOCTOR"
},
"parent":{
"id":1
}
}

...

### Get Query
* Service end point: ** http://localhost:8080/econsult/q/query/<id> **
* HTTP Method: ** GET **
