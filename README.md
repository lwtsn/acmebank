
<h4>Thank you for taking the time to review my submission for the AcmeBank Account Manager.</h4>  
  
<p>The layout of the project is simple MVC using Spring Boot. It has one controller which allows a user to query their balance and   
transfer between accounts.</p>  
  
<p>The endpoint to query is http://localhost:8080/account/{account_number}</p>  
  
<p>The endpoint to transfer is http://localhost:8080/transfer</p>  
  
The request must be POSTed and have a JSON payload of
  

    {  
         initiatorId; //Who is sending the money
	     benefactorId; //Who is receiving the money
	     amountToTransfer; //How much is being sen
    }

Running the tests can be done by running `./mvnw test`

Cucumber tests should run alongside the unit tests,  if not they can be found under `src/test/resources/features/` Intellij should allow you to right click and run each Scenario 

> Notes:
> - I omitted the concept of a Customer as I felt it would have added    unnecessary complexity
> - Logging is minimal, I think the application could benefit with more verbose logging, especially when transferring funds
> - Database interactions are not transactional
> - I considered adding a transaction log but thought this could be considered a nice to have
