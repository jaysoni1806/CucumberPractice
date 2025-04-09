Feature: Create a Bug

  Scenario: Navigate to New Issue screen
    Given Project dropdown should be present
    When  Click on project dropdown and select "QA Internal" project
    Then  User should redirect on the Project Overview screen
    And   Click on Issues tab and verify that Issue screen is present or not
    And   Check that New issue button and click on it if present

  Scenario: Create a New Bug
    Given User should be on the New Issue Screen
    And   Fill all the required fields in the form
    When  Click on Create button
    Then  New issue should create
