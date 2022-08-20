Feature: Retrieve transaction status endpoint

  Scenario: Retrieve transaction details - provide existing txId
    Given transaction status is retrieved for existing txId: "a5862a0f-79dd-4e8a-ac67-27b7aa779c91", Then transaction status should be returned

  Scenario: Retrieve transaction details - provide non-existent txId
    Given transaction status is retrieved for non-existent txId: "0x0", Then transaction status retrieval should fail
