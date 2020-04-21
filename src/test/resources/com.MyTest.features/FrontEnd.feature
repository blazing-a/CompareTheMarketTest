Feature: Frontend test cases

  @web
  Scenario: Test 1

	Given I navigate to the Coin Market Website
    Then I should see the "Coin Market Cap" page
    And I click the "View All" button
    And I should see that the "Cryptocurrencies" table has "100" items

    @web
  Scenario: Test 2

    Given I navigate to the Coin Market Website
    Then I should see the "Coin Market Cap" page
    And I click the "a Random Cryptocurrency" ellipsis

    