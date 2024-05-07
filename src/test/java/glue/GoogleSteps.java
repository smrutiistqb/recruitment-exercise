package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSteps {

    @Given("url {string} is launched")
    public void url(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    @When("About page is shown")
    public void pageIsShown() {
        W.get().driver.findElement(By.linkText("About")).click();
    }

    @Then("page displays {string}")
    public void pageIsShown(String expectedText) {
        String actualText = W.get().driver.findElement(By.xpath("//p[contains(text(), 'Our mission is to')]")).getText();
        Assert.assertEquals(expectedText, actualText);
    }

    @When("searching for {string}")
    public void searching_for(String searchTerm) {
        WebElement searchBox = W.get().driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Then("results contain {string}")
    public void results_contain(String expectedTitle) {
        List<WebElement> resultLinks = W.get().driver.findElements(By.tagName("h3"));
        boolean found = false;
        for (WebElement link : resultLinks) {
            if (link.getText().contains(expectedTitle)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new AssertionError("Expected title not found in search results: " + expectedTitle);
        }
    }

    @Then("result stats are displayed")
    public void result_stats_are_displayed() {
        WebElement resultStats = W.get().driver.findElement(By.id("result-stats"));
        Assert.assertTrue(resultStats.isDisplayed());

    }

    @Then("number of {string} is more than {int}")
    public void number_of_is_more_than(String item, int threshold) {
        if (item.equals("results")) {

            String actualText = W.get().driver.findElement(By.xpath("//*[@id='result-stats']/text()")).getText();
            String value = actualText.substring(actualText.indexOf(" "), actualText.lastIndexOf(" ")).trim();
            value = value.replace(",", "");
            int number = Integer.parseInt(value);
            Assert.assertTrue(number > threshold);

        } else if (item.equals("seconds")) {
            WebElement resultStats = W.get().driver.findElement(By.id("result-stats"));
            String text = resultStats.getText();
            String[] parts = text.split("\\(");
            String timePart = parts[1]; // Get the second part, which contains the time
            String time = timePart.split("\\)")[0];
            String[] timeParts = time.split(" ");
            double seconds = Double.parseDouble(timeParts[0]);
            Assert.assertTrue(seconds > threshold);

        }
    }

}
