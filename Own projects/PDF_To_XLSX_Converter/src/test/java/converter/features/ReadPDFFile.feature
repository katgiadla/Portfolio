# Created by MTchorek at 23-Feb-20
Feature: Read PDF feature
  This feature describes cases PDF file reading

  Scenario: Read PDF file
    Given One PDF file
    When Service tries to get its content
    Then Service has file content
    And No exception related with PDF should be thrown


  Scenario: PDF not found
    Given No PDF file
    When Service tries to get its content
    Then Exception related with PDF should be thrown