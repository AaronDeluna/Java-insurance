package org.javaacademy.insurance.service.brazil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.javaacademy.insurance.insurance_objects.InsuranceType.MEDICAL;
import static org.javaacademy.insurance.insurance_objects.InsuranceType.THEFT;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("brazil")
@SpringBootTest
class InsuranceCalcBrazilServiceTest {
    @Autowired
    private InsuranceCalcBrazilService insuranceCalcBrazilService;
    @Test
    @DisplayName("Успешный расчет стоимости страхования от ограбления")
    void testSuccessfulRobberyInsuranceCostCalculation() {
        BigDecimal expected = valueOf(2_800);
        BigDecimal result = insuranceCalcBrazilService.calculateInsuranceCost(valueOf(50_000), THEFT);
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    @DisplayName("Успешный расчет стоимости медицинского страхования")
    void testSuccessfulMedicalInsuranceCalculation() {
        BigDecimal expected = valueOf(6_800);
        BigDecimal result = insuranceCalcBrazilService.calculateInsuranceCost(valueOf(200_000), MEDICAL);
        assertEquals(0, expected.compareTo(result));
    }
}