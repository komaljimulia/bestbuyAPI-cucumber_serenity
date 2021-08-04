Feature: Testing different request on the best buy categories application

  Scenario: verify the user can access in best buy application
    When user send a GET request to categories endpoint,they must get back a valid status code 200

  Scenario: create a new categories and verify if categories is added
    When  I create new categories by providing the information name and id
    Then I verify the categories is created

  Scenario: Getting the categories request by id
    When  I send GET request to endpoint with id "id", I should received the categories information

  Scenario: update a created categories and verify if the categories record is updated
    When  I update a created categories providing the new name
    Then  I verify the categories is updated

  Scenario: Delete a created categories & verify the categories is deleted
    When I delete a created categories ,they must get back a valid status code is 200
    Then I verify the categories is deleted



