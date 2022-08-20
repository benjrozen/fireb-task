Feature: Delete user endpoint


  Scenario: Delete user - existing id
    Given a user is created
    When the user is deleted, he should no longer appear in search
  Scenario: Delete user - non-existent id
    Given non-existent user id "1"
    When the user is deleted, the deletion should fail