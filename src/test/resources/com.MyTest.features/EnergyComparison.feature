Feature: Frontend test cases

  @web
  Scenario: Electricity user Journey

	Given I navigate to the Energy Comparison Website
    Then I should see the "Your supplier" page
    When I enter "PE2 6YS" text into the "Post Code" field
    And I select "No, I don’t have my bill" for the "Do you have your energy bill to hand?" question
    And I select "Gas" for the "What would you like to compare?" question
    And I select "British Gas" for the "Who is your current electricity supplier?" question
    And I click the "Next" button
    Then I should see the "Your Energy" page
    When I select "Yes" for the "Do you use a prepayment ‘pay as you go’ meter?" question
    And I enter "50" text into the "Currency" field
    And I click the "Next" button
    Then I should see the "Your Details" page
    And I select "Fixed tariff" for the "What tariff are you interested in?" question
    And I enter "me@you.com" text into the "Email Address" field
    And I select "Fixed tariff" for the "What tariff are you interested in?" question
    And I select "Do not contact" for the "Let us keep you up to date" section
    And I select "Confirm" for the "Terms & Conditions" section
    And I click the "Show Me Deals" button

  @web
  Scenario: Electricity and Gas User Journey

    Given I navigate to the Energy Comparison Website
    Then I should see the "Your supplier" page
    When I enter "PE2 6YS" text into the "Post Code" field
    And I select "Yes, I have my bill" for the "Do you have your energy bill to hand?" question
    And I select "Gas & electricity" for the "What would you like to compare?" question
    And I select "Yes" for the "Is your gas and electricity from the same supplier?" question
    And I select "EDF Energy" for the "Who is your current energy supplier?" question
    And I click the "Next" button
    Then I should see the "Your Energy" page
    When I select "Better Together May21" for the "Energy Plan" dropdown
    And I select Yes for the "Do you have an Economy 7 meter?" question
    And I select "Monthly Direct Debit" for the "How do you pay for your electricity?" question
    And I select No for the "Is electricity your main source of heating?" question
    And I select "£" for the "What is your current electricity usage?" question
    And I enter "50" text into the "Electricity Amount" field
    And I enter "17"-"06"-"2020" into the Date field
    And I click the "Next" button
    Then I should see the "Your gas" screen
    When I select "Better Together May21" for the "Energy Plan" dropdown
    And I select "Monthly Direct Debit" for the "How do you pay for your gas?" question
    And I select "£" for the "What is your current gas usage?" question
    And I enter "100" text into the "Gas Amount" field
    And I enter "17"-"03"-"2020" into the Date field
    And I click the "Next" button
    Then I should see the "Your Details" page
    And I select "Fixed tariff" for the "What tariff are you interested in?" question
    And I select "Monthly Direct Debit" for the "How do you want to pay for your energy?" question
    And I enter "me@you.com" text into the "Email Address" field
    And I select "Fixed tariff" for the "What tariff are you interested in?" question
    And I select "Do not contact" for the "Let us keep you up to date" section
    And I select "Confirm" for the "Terms & Conditions" section
    And I click the "Show Me Deals" button





    