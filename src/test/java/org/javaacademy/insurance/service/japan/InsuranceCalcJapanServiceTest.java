package org.javaacademy.insurance.service.japan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.javaacademy.insurance.insurance_objects.InsuranceType.MEDICAL;
import static org.javaacademy.insurance.insurance_objects.InsuranceType.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("japan")
@SpringBootTest
class InsuranceCalcJapanServiceTest {
    @Autowired
    private InsuranceCalcJapanService insuranceCalcJapanService;

    @Test
    @DisplayName("Успешный расчет стоимости страховки от ограбления")
    void testSuccessfulRobberyInsuranceCalculation() {
        BigDecimal expected = valueOf(20_000);
        BigDecimal result = insuranceCalcJapanService.calculateInsuranceCost(
                valueOf(1_000_000), THEFT);
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    @DisplayName("Успешный расчет стоимости медицинского страхования")
    void testSuccessfulMedicalInsuranceCalculation() {
        BigDecimal expected = valueOf(162_000);
        BigDecimal result = insuranceCalcJapanService.calculateInsuranceCost(
                valueOf(10_000_000), MEDICAL);
        assertEquals(0, expected.compareTo(result));
    }
}