package org.javaacademy.insurance.servise;

import lombok.RequiredArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.utils.CalculateCostUtils;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@RequiredArgsConstructor
public abstract class InsuranceCalcCountryService implements InsuranceCalcService {
    private final InsuranceCalculationProperties properties;
    private BigDecimal price = ZERO;

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
            case THEFT -> price = CalculateCostUtils.calculateCost(insuredAmount,
                    properties.getRobberyCoefficient(), properties.getTheftServiceCost());
            case MEDICAL -> price = CalculateCostUtils.calculateCost(insuredAmount,
                    properties.getMedicalCaseCoefficient(), properties.getMedicalServiceCost());
        }
        return price;
    }
}
