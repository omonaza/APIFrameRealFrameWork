@GorestUsers @Regression @Smoke
Feature: Users

  @successfullyCreateUser
  Scenario: Successfully create a user
    Given the following user has been created:
      | name  | email             | gender | status |
      | Baias | afnvld1@gmail.com | Male   | Active |
    Then verify that status code is 200
    Then the following user has been returned:
      | name  | email             | gender | status |
      | Baias | afnvld1@gmail.com | Male   | Active |