Feature: Update user endpoint

  Scenario: Update existing user with valid balance and name
    Given existing user with id "386", when updating user name to "Shlomi" and balance to 9, then user name and balance should be updated

  Scenario: Update existing user, provide name and negative value for balance
    Given existing user with id "386", when updating user name to "Shlomi" and balance to -99, then update user should fail, balance cannot be a negative number

  Scenario: Update existing user, provide name and value with decimal for balance
    Given existing user with id "386", when updating user name to "Shlomi" and balance to 99.3452435, then update user should fail, balance must be a natural number

  Scenario: Update user - provide non-existent user id
    Given non-existent user with id "1", when updating user name to "Shlomi" and balance to 134, then update user should fail
