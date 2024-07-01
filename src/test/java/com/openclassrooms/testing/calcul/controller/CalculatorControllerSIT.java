package com.openclassrooms.testing.calcul.controller;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.service.CalculatorService;
import com.openclassrooms.testing.calcul.service.SolutionFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = { CalculatorController.class, CalculatorService.class })
@ExtendWith(SpringExtension.class)
class CalculatorControllerSIT {
    @Inject
    private MockMvc mockMvc;

    @MockBean
    private Calculator mockCalculator;
    @MockBean
    private SolutionFormatter mockSolutionFormatter;

    @Test
    void givenCalculatorApp_whenRequestAddition_thenSolutionIsShown() throws Exception {
        // GIVEN
        when(mockCalculator.add(2, 3)).thenReturn(5);

        // WHEN
        final MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/calculator")
                        .param("leftArgument", "2")
                        .param("rightArgument", "3")
                        .param("calculationType", "ADDITION"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        // THEN
        verify(mockCalculator).add(2, 3);
        verify(mockSolutionFormatter).format(5);
        assertThat(mvcResult.getResponse().getContentAsString())
                .contains("id=\"solution\"")
                .contains(">5</span>");
    }
}
