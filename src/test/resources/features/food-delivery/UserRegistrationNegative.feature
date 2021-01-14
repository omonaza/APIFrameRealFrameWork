@UserRegistration @NEGATIVE @Regression @Smoke
Feature: User Registration - Negative

  @userRegistersWithExistingUsername
  Scenario: User registers with existing username
    Given user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | baias21  | Test123  | John Doe | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 200
    When user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | baias21  | Test123  | John Doe | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then the following error message has been returned:
      | status           | errorMessage                                    |
      | Bad Request Body | Username unavailable. Please choose another one |