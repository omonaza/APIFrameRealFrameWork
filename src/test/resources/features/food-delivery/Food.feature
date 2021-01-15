@FoodManagement @Regression @Smoke
Feature: Food Management

  @addNewFoodEntry
  Scenario Outline: Add new food entry
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType   |
      | Wine        | https:foods.com | 20.00 | Merlot | <foodType> |
    Then verify that status code is 200
#    Then the following food has been added:
#      | description | imageUrl        | price | name   | foodType  |
#      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Examples:
      | foodType   |
      | Beverages  |
      | Appetizers |
      | MainDish   |

  @addNewFoodEntryWithoutDescription
  Scenario: Add new food entry without description
    Given add new food to FoodDelivery with the following fields
      | imageUrl        | price | name   | foodType  |
      | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200
    Then the following food has been added:
      | imageUrl        | price | name   | foodType  |
      | https:foods.com | 20.00 | Merlot | Beverages |

  @updateFoodEntry
  Scenario: Update food entry
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name | foodType  |
      | Beer        | https:foods.com | 20.00 | Bud  | Beverages |
    Then verify that status code is 200
    When food entry "price" is updated with the following fields
      | description | imageUrl        | price  | name | foodType  |
      | Beer        | https:foods.com | 100.00 | Bud  | Beverages |
    Then verify that status code is 200
    Then the following food has been added:
      | description | imageUrl        | price  | name | foodType  |
      | Beer        | https:foods.com | 100.00 | Bud  | Beverages |