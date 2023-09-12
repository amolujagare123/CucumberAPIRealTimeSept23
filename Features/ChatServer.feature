Feature: all chat server requests

  @listChatUser
  Scenario: Verify chat server users are listed correctly for given pages
    Given List User given request for chat server is created with query parameter
    When user calls List user Request for chat server with Get http request
    Then the API call for chat server got success with status code 200
    And total users for chat server should be 200