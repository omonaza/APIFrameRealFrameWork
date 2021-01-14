@UserRegistration @Regression @Smoke
Feature: User Registration
  As a User
  I want to be able to register to the app,
  so I can use the food delivery service.

  @SAR-202 @userSuccessfullyRegistered
  Scenario: User successfully registered
    Given user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | baias22  | Test123  | John Doe | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 200
    Then the following user has been registered:
      | status                       | username | fullName |
      | User registration successful | baias22  | John Doe |