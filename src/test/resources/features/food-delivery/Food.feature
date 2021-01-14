@FoodManagement @Regression @Smoke
Feature: Food Management

  @addNewFoodEntry
  Scenario: Add new food entry
    Given add new food to FoodDelivery with the following fields
      |description|imageUrl       | price   |  name     |  foodType   |
      |Wine       |https:foods.com| 20.00   |  Merlot   |  Beverages  |
    Then verify that status code is 200
    Then verify that food has been successfully added
