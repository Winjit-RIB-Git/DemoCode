Feature: Test for Smart Module

  Background: 
    Given Launch The Application
    When Login The Application With Sample Credential

  @Sanity
  Scenario: Testing Submodule - Triggers & Alerts submodule
    Then Selecting the Smart Action from Side Bar
    Then Selecting the Trigger & Alerts Option
    #And To Create New Trigger Click on New
    Then Create New Trigger & Enter the Following Details
      | Name           | Thing     | Description              | Condititon                                 | Action |
      | TriggerTest_8  | machine 1 | This is For Test Purpose | data['temp']>100 &&  _previous['temp']<100 | MQTT   |
      #| TriggerTest_9  | machine 2 | This is For Test Purpose | data['temp']>100 &&  _previous['temp']<100 | REST   |
      #| TriggerTest_10 | machine 3 | This is For Test Purpose | data['temp']>100 &&  _previous['temp']<100 | EMAIL  |
      #| TriggerTest_11 | machine 4 | This is For Test Purpose | data['temp']>100 &&  _previous['temp']<100 | SYNC   |
      #| TriggerTest_12 | test      | This is For Test Purpose | data['temp']>100 &&  _previous['temp']<100 | SMS    |
    #And Clicking on Submit to Create Trigger
    Then Signout the Application
    
    
 
    
