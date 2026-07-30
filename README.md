# Automation Testing POC

Selenium + TestNG POC for two scenarios: basic page assertions, and a restaurant search on Google Maps.

## What's here

**Part 1** - loads wikipedia.org and checks the title plus a couple of visible elements (logo, search box, search button).

**Part 2** - goes to Google Maps, searches "Restaurants", and checks that at least one result shows up.

## Tools used

- Java + Selenium for the browser automation.
- TestNG for running the tests and the assertions.
- WebDriverManager so I don't have to manually download/manage chromedriver versions.
- Maven to build and run everything with one command.
- Basic Page Object pattern - locators live in a separate class from the actual test/assertions, so if a locator breaks I only fix it in one place.

## Structure


src/test/java/com/poc/

  base/     -> BaseTest, opens/closes the browser before and after each test
  
  pages/    -> one class per page, holds locators and simple actions
  
  tests/    -> the actual test methods with assertions


## How to run it

Need Java 17+, Maven, and Chrome installed.

'mvn clean test'


Runs headless by default. Add `-Dheadless=false` if you want to watch
the browser do its thing.

## Notes / things I'd flag

- Google Maps doesn't use stable class names (they're generated at build time and change), so instead of picking a CSS class I locate
  results using the fact that every result link contains "/maps/place/" in its href. Held up fine when I tested it, but it's
  still coupled to Google's markup.
- Didn't handle CAPTCHAs or anything like that - if Google throws one at the automated browser, the test will just fail. Wasn't in scope for this POC.
- Only tested on Chrome.
- The assertion is just "at least one result" rather than checking a specific restaurant name, since actual search results change all     	the time and I didn't want a flaky test.
- With more time I'd add a GitHub Actions workflow to run this on every push, and maybe a retry on the Maps test since it's hitting a
  live site that can occasionally act up.
