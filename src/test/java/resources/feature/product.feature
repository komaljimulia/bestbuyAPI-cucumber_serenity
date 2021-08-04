Feature:  Testing different request on the best buy product application

  #@Smoke
  Scenario: check if the best buy product application can be accessed by user
    When User sends a GET request to products endpoint, they must get back a valid status code 200

  Scenario Outline: Create a new product and verify if the product is added
    When I create a new product by providing the information name type price"<price>" upc Shipping descriptions"<descriptions>"
    Then I verify the product is created
    Examples:
      | price | descriptions                                             |
      | 89.99 | This is a placeholder request for creating a new product |

  Scenario: Getting the product information by Id
    When  I send the Get request to product endpoint with ID "id",I should received the product information

  Scenario: update a created product and verify if the product is updated
    When  I update a created product providing the new name upc price and description
    Then I verify the product is updated

  Scenario: Delete a created product & verify the product is deleted
    When I delete a created product ,they must get back a valid status code  is 200
    Then I verify the product is deleted



