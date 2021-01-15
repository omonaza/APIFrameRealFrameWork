@FoodManagement @NEGATIVE @Regression @Smoke
Feature: Food Management - Negative

  @addNewFoodToFoodDeliveryWithMissingFields
  Scenario: Add new food to FoodDelivery with missing fields
    Given add new food to FoodDelivery with the following fields
      | description | price | name   | foodType  |
      | Wine        | 20.00 | Merlot | Beverages |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food image url cannot be null or empty."
#    When add new food to FoodDelivery with the following fields
#      | description | name   | foodType  | imageUrl        |
#      | Wine        | Merlot | Beverages | https:foods.com |
#    Then verify that status code is 403
#    Then verify response error message "Invalid request - Food price cannot be null or empty."
    When add new food to FoodDelivery with the following fields
      | description | foodType  | imageUrl        | price |
      | Wine        | Beverages | https:foods.com | 20.00 |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food name cannot be null or empty."
    When add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   |
      | Wine        | https:foods.com | 20.00 | Merlot |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food type cannot be null or empty."