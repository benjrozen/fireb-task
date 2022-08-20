Feature: Retrieve single user endpoint

  Scenario: Retrieve user - existing id
    When a single user is retrieved with a existing user id: "386", user details should be returned

  Scenario: Retrieve user - non-existent id
    When a single user is retrieved with a non-existent user id: "00adfsds", User retrieval should fail
