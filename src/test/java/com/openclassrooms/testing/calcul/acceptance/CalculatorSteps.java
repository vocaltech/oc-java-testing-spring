package com.openclassrooms.testing.calcul.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorSteps {
    @Inject
    MockMvc mockMvc;
    private Integer lastLeftArgument;
    private Integer lastRightArgument;
    private String calculationType;

    @Given("un élève utilise le Calculateur")
    // TODO
    public void a_student_is_using_the_Calculator() {
        System.out.println("a_student_is_using_the_Calculator()");
    }

    @When("{int} et {int} sont additionnés")
    // TODO
    public void and_are_added(Integer leftArgument, Integer rightArgument) {
        System.out.println("and_are_added(): leftArg" + leftArgument + " - rightArg: " + rightArgument);
    }

    @Then("on montre {int} à l'élève")
    // TODO
    public void the_student_is_shown(Integer expectedResult) {
        System.out.println("the_student_is_shown(): expectedRes: " + expectedResult);
    }
}
