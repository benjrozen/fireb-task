Feature: Create user endpoint

  Scenario: Create user, provide all need values (name + balance)
    When a user is created with name "Ben Rozen" and a balance of 123000, user creation should succeed

  Scenario: Create user, provide name and negative value for balance
    When a user is created with name "Ben Rozen" and a balance of -123000, user creation should fail, balance cannot be a negative number when creating a user

 Scenario: Create user, provide name and value with decimal for balance
    When a user is created with name "Ben Rozen" and a balance of 10.50, user creation should fail, balance must be a natural number