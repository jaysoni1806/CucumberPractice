Feature: Create a Bug

  Scenario: Create a new bug
    Given Project dropdown should be present
    When  click on project dropdown and select "QA Internal" project
    Then  user should redirect on the Project Overview screen