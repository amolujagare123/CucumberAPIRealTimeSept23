Feature: all sample rest (reqres.in) requests

  @listAllUsers @sample
  Scenario: Verify users are listed correctly for given pages
    Given List User given request is created with query parameter
    When user calls List user Request with Get http request
    Then the API call got success with status code 200
    And total users should be 6

  @CreateUser @sample
  Scenario: Verify user is created successfully
    Given Create User given request is created
    When user calls List user Request with POST http request
    Then the API call got success with status code 201
    #And Job should be leader
    And "job" should be "leader"
    And "name" should be "morpheus"

