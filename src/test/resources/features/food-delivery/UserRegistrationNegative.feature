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

  @userRegisterWithEmptyOrNullUsername
  Scenario: User registers with empty or null username
    Given user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      |          | Test123  | John Doe | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then the following error message has been returned:
      | status           | errorMessage                     |
      | Bad Request Body | Username cannot be null or empty |
    When user registers to food delivery app with the following fields:
      | password | fullName | address     | city    | state | zip   | phone     |
      | Test123  | John Doe | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then the following error message has been returned:
      | status           | errorMessage                     |
      | Bad Request Body | Username cannot be null or empty |

  @userRegisterWithEmptyOrNullFullName
  Scenario: User registers with empty or null full name
    Given user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | test1    | Test123  |          | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then the following error message has been returned:
      | status           | errorMessage                     |
      | Bad Request Body | Fullname cannot be null or empty |
    When user registers to food delivery app with the following fields:
      | username | password | address     | city    | state | zip   | phone     |
      | test2    | Test123  | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then the following error message has been returned:
      | status           | errorMessage                     |
      | Bad Request Body | Fullname cannot be null or empty |

  @userRegisterWithEmptyOrNullPassword
  Scenario: User registers with empty or null password
    Given user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | test1    |          | Jphn Des | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 500
    Given user registers to food delivery app with the following fields:
      | username | fullName | address     | city    | state | zip   | phone     |
      | test1    | Jphn Des | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 500