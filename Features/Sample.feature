Feature: all sample rest (reqres.in) requests

  Scenario: Verify users are listed correctly for given pages
    Given List User given request is created with query parameter
    When user calls List user Request with Get http request
    Then the API call got success with status code 200