package com.openclassrooms.testing.calcul.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {
    public static final String ADDITION_SYMBOL = "+";
    public static final String SUBTRACTION_SYMBOL = "-";
    public static final String  MULTIPLICATION_SYMBOL = "x";
    public static final String DIVISION_SYMBOL = "/";
    @FindBy(id = "left")
    private WebElement leftField;
    @FindBy(id = "right")
    private WebElement rightField;
    @FindBy(id = "type")
    private WebElement calculationType;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(id = "solution")
    private WebElement solution;
    private final WebDriver webDriver;
    public CalculatorPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public String add(String leftValue, String rightValue) {
        return calculate(ADDITION_SYMBOL, leftValue, rightValue);
    }
    public String subtract(String leftValue, String rightValue) {
        return calculate(SUBTRACTION_SYMBOL, leftValue, rightValue);
    }
    public String multiply(String leftValue, String rightValue) {
        return calculate(MULTIPLICATION_SYMBOL, leftValue, rightValue);
    }
    public String divide(String leftValue, String rightValue) {
        return calculate(DIVISION_SYMBOL, leftValue, rightValue);
    }
    private String calculate(String calculationTypeValue, String leftValue, String rightValue) {
        calculationType.sendKeys(calculationTypeValue);
        leftField.sendKeys(leftValue);
        rightField.sendKeys(rightValue);
        submitButton.click();

        WebDriverWait waiter = new WebDriverWait(webDriver, 5);
        waiter.until(ExpectedConditions.visibilityOf(solution));

        return solution.getText();
    }
}
