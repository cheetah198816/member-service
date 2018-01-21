**Member Microservice using Spring Boot**

This microservice contains one main module :

1. **member-microservice** - 
	
	1. This module contains endpoints for the creation of the new members.
	2. It also handles the fetching, updation and deletion of the existing members.
	2. It runs on port of 9090.
	3. The main class to start the module is MemberApplication.java.

The other two modules :

**member-api** : contains the dtos.

**member-model** : contains the entities.
 
**Database** :

The database used is In Memory Database(H2).

**Basic Authentication** :

Basic Authentication is supported. The credentials are 

**Username** : user

**Password** : abcd1234

**Endpoints Test** :

You can test the application REST endpoints through Postman.
Please install Postman and login using the below credentials and find the relevant endpoints in the MemberService Collection :

Username : cheetah198816

Password : abcd1234

