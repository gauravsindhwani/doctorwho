# First time setup

## Database setup

1. Install Mysql
2. DDL script location <projectlocation>/econsult/DataModel/econsultModel.sql
3. Setup basic data scriptLocation: <projectlocation>/econsult/DataModel/seedDataSetup.sql

## Project setup

1. MVN clean install

## Start server

1. Jetty server can be started as a standalone java application. The location of main class is : /econsult/webapp/src/main/java/com/econsult/main/ServerInitializer.java
2. The server is listening on port 8080

## Api 

### Create Service plan

* Service end point:**http://localhost:8080/econsult/admin/plan**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```

{
"name": "BASIC-YEARLY",
"duration": "YEARLY",
"numberOfDependents": "4",
"fee": "1000"
}
```

### Create Corporation

* Service end point:**http://localhost:8080/econsult/admin/corp/**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
"name": "AbcCorp",
"email": "con106@abc.com",
"primaryPhone": "0274239784",
"domain": "abc.com"
}

```

### Create account

* Service end point:**http://localhost:8080/econsult/admin/account/**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
"corpEmail":"gaurav@abc.com"
}

```

### Create patient

* Service end point:**http://localhost:8080/econsult/u/patient/**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
  "firstName" : "sangeet",
  "contactInfo" : {
    "email" : "sang@gmail.com"
  },
  "accountId": 1,
 
  "medicalInfo" : {
    "gender" : "FEMALE",
    "weight" : 100,
    "allergies" : "sulphur",
    "dobToDisplay" : "2013-02-11"
  }
}

```
### update patient

* Service end point:**http://localhost:8080/econsult/u/patient/{id}**
* content-type:**Content-Type: application/json**
* HTTP Method:**PUT**
* Payload 
```
{
  "firstName" : "sangeet",
  "contactInfo" : {
    "email" : "sang@gmail.com"
  },
  "accountId": 1,
 
  "medicalInfo" : {
    "gender" : "FEMALE",
    "weight" : 100,
    "allergies" : "sulphur",
    "dobToDisplay" : "2013-02-11"
  }
}

```

### create medical info

Medical info can be created as part of creating a patient, or it can be created seperately using this api.

* Service end point:**http://localhost:8080/econsult/u/medicalinfo**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
patientId: 11
updatedOnDate: 1417260963000
gender: "FEMALE"
weight: 100
allergies: "sulphur"
dobToDisplay: "2013-02-11"
}

```

### get medical info

* Service end point:**http://localhost:8080/econsult/u/medicalinfo/{id}**
* content-type:**Content-Type: application/json**
* HTTP Method:**GET**

### update medical info

* Service end point:**http://localhost:8080/econsult/u/medicalinfo/{id}**
* content-type:**Content-Type: application/json**
* HTTP Method:**PUT**
* Payload 
```
{
patientId: 11
updatedOnDate: 1417260963000
gender: "FEMALE"
weight: 100
allergies: "sulphur"
dobToDisplay: "2013-02-11"
}

```

### Create Doctor
* Service end point:**http://localhost:8080/econsult/u/doctor/**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
  "firstName" : "doc1",
  "contactInfo" : {
    "email" : "doc1@gmail.com"
  }
}

```
### Get Doctor
* Service end point:**http://localhost:8080/econsult/u/doctor/{ID}**
* HTTP Method:**GET**

### Create Query
* Service end point:**http://localhost:8080/econsult/q/query/**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
"text":"hi doc wassup",
"patientId": "4"
}


```
### Get Query
* Service end point:**http://localhost:8080/econsult/q/query/{ID}**
* HTTP Method:**GET**

### Create Post
* Service end point:**http://localhost:8080/econsult/q/post/**
* content-type:**Content-Type: application/json**
* HTTP Method:**POST**
* Payload 
```
{
  "queryId": 3,
  "text" : "doc here i m good",
   "user":{
     "id":13,
     "role":"DOCTOR"
   }
 }

```

### Get POST
* Service end point:**http://localhost:8080/econsult/q/post/{ID}**
* HTTP Method:**GET**

