Feature: Retrieve users endpoint

  Scenario: Retrieve all users
    When all users are retrieved, a list of existing users and their details should be returned

  Scenario: Retrieve all users, use query string parameter - existing user/s
    When users are retrieved using query string param "name" "Ben Rozen" for existing user, a list of the relevant users and their details should be returned

  Scenario: Retrieve all users, use query string parameter - non-existent user/s
    When users are retrieved using query string param "name" "#@$^#$%^@#$^" for existing user, An empty list should be returned