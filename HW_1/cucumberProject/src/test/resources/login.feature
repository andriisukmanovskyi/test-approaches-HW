Feature: Gmail login
  Enter username and password to login Gmail

  Background: Open login page and enter userName
    Given Open gmail login page
    When User enters username: tt932101@gmail.com and clicks 'next'

  Scenario: Successful login
    And User enters password: qwertyuiop[]\ and clicks 'next'
    Then Navigation to Gmail inbox page with title contains 'Inbox'

  Scenario: Failed login with wrong password
    And User enters password: 5as4faas5f4 and clicks 'next'
    Then Wrong password message is displayed