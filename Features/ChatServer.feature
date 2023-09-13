Feature: all chat server requests

  @listChatUser @ChatUser
  Scenario: Verify chat server users are listed correctly for given pages
    Given List User given request for chat server is created with query parameter
 #   When user calls List user Request for chat server with Get http request
    When user calls "listUser" for chat server with "GET" http request
    Then the API call for chat server got success with status code 200
    And total users for chat server should be 200


  @createChatUser  @ChatUser
  Scenario: Verify chat server users is created successfully
    Given create User given request for chat server is created
    When user calls "createUser" for chat server with "POST" http request
    Then the API call for chat server got success with status code 200
    And "result.name" should be "Parameshwari" in the json output