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
    Then  New issue should create and Back to issue screen

    #Add Start date and Due date in newly created issue.
    Scenario: Update a bug
      Given User should be on bug details screen.
      And   Click on Edit
      When  Set Start date and Due date for the existing opened issue
      And   Click on Submit button
      Then  Issue should be update
