Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

  Scenario: User successfully changes password with valid credentials
    Given the user is logged in with username "steven_king" and password "oldPassword"
    When the user navigates to the "Change Password" page
    And enters their current password "oldPassword"
    And enters a new password "newPassword" that meets the password policy guidelines
    And confirms the new password by retyping "newPassword"
    And submits the change password form
    Then the user should see a success message confirming the password change
    And the user should be logged out
    And the user should be able to log in with the new password

Scenario: User attempts to change password with invalid current password
    Given the user is logged in with username "steven_king" and password "oldPassword"
    When the user navigates to the "Change Password" page
    And enters an incorrect current password "wrongPassword"
    And enters a new password "newPassword" that meets the password policy guidelines
    And confirms the new password by retyping "newPassword"
    And submits the change password form
    Then the user should see an error message indicating the current password is incorrect
    And the user should remain on the "Change Password" page


      Scenario: User attempts to change password with new password that does not meet policy guidelines
        Given the user is logged in with username "steven_king" and password "oldPassword"
        When the user navigates to the "Change Password" page
        And enters their current password "oldPassword"
        And enters a new password "password" that does not meet the password policy guidelines
        And confirms the new password by retyping "weak"
        And submits the change password form
        Then the user should see an error message indicating the new password does not meet the policy guidelines
        And the user should remain on the "Change Password" page

