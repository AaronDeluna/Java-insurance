package org.javaacademy.insurance.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.insurance_objects.InsuranceType;
import org.javaacademy.insurance.utils.CalculateCostUtils;

import java.math.BigDecimal;

@RequiredArgsConstructor
public abstract class AbstractInsuranceCalcService implements InsuranceCalcService {
    private final InsuranceCalculationProperties properties;

    /**
     * Расчет стоимости страховки в Японии.
     *
     * @param insuredAmount сумма покрытия.
     * @param insuranceType тип старховки.
     * @return стоимоть страховки.
     */
    @Override
    public BigDecimal calculateInsuranceCost(BigDecimal insuredAmount, InsuranceType insuranceType) {
        switch (insuranceType) {
            case THEFT -> {
                return CalculateCostUtils.calculateCost(insuredAmount,
                        properties.getRobberyCoefficient(), properties.getTheftServiceCost());
            }
            case MEDICAL -> {
                return CalculateCostUtils.calculateCost(insuredAmount,
                        properties.getMedicalCaseCoefficient(), properties.getMedicalServiceCost());
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
