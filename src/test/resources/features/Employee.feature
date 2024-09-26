Feature: Validate Employee Functionality

  Scenario: verify user can create the employee
    Given user wants to call "/create" endpoint
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_employee.json"
    When user performs the post call
    Then verify the status code is 200


  Scenario: verify the user get the employee
    And verify the booking id is not empty
    And stores the id into "booking.id"
    When user wants to call "/employee/@id" endpoint
    And user performs the get call
    Then verify the status code is 200

  Scenario Outline: verify the user can update employee details
    When user wants to call "/update/@id" endpoint
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_employee.json" and update using pojo "<name>"
    When user performs the put call
    Then verify the status code is 200
    Examples:
      | name       |
      | Foundation |

  Scenario: verify the user can delete employee
    When user wants to call "/delete/@id" endpoint
    When user performs the delete call
    Then verify the status code is 200
