package com.openclassrooms.testing.calcul.e2e;

import com.openclassrooms.testing.calcul.e2e.page.CalculatorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentMultiplicationJourneyE2ETest {
    @LocalServerPort
    private Integer port;
    private WebDriver webDriver;
    private String baseUrl;

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupWebDriver() {
        webDriver = new ChromeDriver();
        baseUrl = "http://localhost:" + port + "/calculator";
    }

    @AfterEach
    void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void aStudentUsesTheCalculatorToMultiplyTwoBySixteen() {
        // GIVEN
        webDriver.get(baseUrl);
        WebElement leftField = webDriver.findElement(By.id("left"));
        WebElement rightField = webDriver.findElement(By.id("right"));
        WebElement typeDropDown = webDriver.findElement(By.id("type"));
        WebElement submitButton = webDriver.findElement(By.id("submit"));

        // WHEN
        leftField.sendKeys("2");
        rightField.sendKeys("16");
        typeDropDown.sendKeys("x");
        submitButton.click();

        // THEN
        WebDriverWait waiter = new WebDriverWait(webDriver, 5);
        WebElement solutionElement = waiter.until(
                ExpectedConditions.presenceOfElementLocated(By.id("solution"))
        );

        String solution = solutionElement.getText();
        assertThat(solution).isEqualTo("32");
    }
    @Test
    void aStudentUsesTheCalculatorToMultiplyTwoBySixteen_withPageObject() {
        // GIVEN
        webDriver.get(baseUrl);
        final CalculatorPage calculatorPage = new CalculatorPage(webDriver);

        // WHEN
        final String solution = calculatorPage.multiply("2", "16");

        // THEN
        assertThat(solution).isEqualTo("32");
    }
}
