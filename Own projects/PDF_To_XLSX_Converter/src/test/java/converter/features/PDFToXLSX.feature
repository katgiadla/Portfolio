# Created by MTchorek at 23-Feb-20
Feature: Fetch PDF file data and save it in XLSX file

  Scenario: Successful conversion
    Given PDF file only in the folder
    When Service tries to save content to XLSX file
    Then No exception related with that process should be thrown

  Scenario: UnSuccessful conversion
    Given PDF and XLSX files in the folder
    When Service tries to save content to XLSX file
    Then Exception related with existing xlsx should be thrown