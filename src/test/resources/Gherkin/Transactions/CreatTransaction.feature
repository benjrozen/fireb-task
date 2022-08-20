Feature: Create transaction endpoint

  Scenario: Create transaction - provide valid values (source, destination, amount)
    Given a valid transaction is created with sourceId: 386, destinationId 387, amount 100 - then transaction id should be returned, amount provided should be deducted from source and added to destination

 Scenario: Create transaction - provide amount higher than the source's balance
    Given a user is created with balance of 10
    When invalid transaction is created with sourceId of user created, destinationId 387, amount 999999999 - then transaction should fail, amount cannot be more than source can afford

 Scenario: Create transaction - provide negative amount higher than the destination's balance
   Given a user is created with balance of 10
   When invalid transaction is created with sourceId: 386, destinationId of user created, amount -999999999 - then transaction should fail, amount cannot be more than destination can afford

  Scenario: Create transaction - provide value with decimal for amount
    Given invalid transaction is created with sourceId: 386, destinationId 387, amount 0.3454123 - then transaction should fail, amount must be a natural number
