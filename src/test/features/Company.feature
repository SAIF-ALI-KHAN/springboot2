Feature: For Company  
  Scenario: User calls web service to get all company
	Given get All Companies
	When a user retrieves all companies 
	Then the status code is 200
	
 
Scenario: User calls web service to create company
    Given get the companyDetail for companyCucmberTest
    When I save the new company
    Then verify status code is 200
    And response includes the following
    | companyName	    | companyCucmberTest	|
	
	
	
	Scenario: User calls web service to update company
    Given get the updated companyDetail by id of 62
    When I save the updated company
    Then check status code is 200
    And response includes the following update
    | companyName	    | updatecompanyCucmberTest	|
