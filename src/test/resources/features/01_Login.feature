Feature: Test Login Functionality

#  Scenario Outline: login with invalid credentials
    When user enters invalid "<Username>" and "<Password>"
    Then verify the error
    Examples:
    | Username | Password       |
    |  jay     | 132564         |
    |  jaySoni | Openxcell@2023 |
    |          |                |

  Scenario: Login With Valid Credentials
    When user enters valid username and password
    Then user should redirect to the home page