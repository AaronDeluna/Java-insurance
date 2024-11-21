package org.javaacademy.insurance.service.brazil;

import lombok.RequiredArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.service.InsuranceCalcService;
import org.javaacademy.insurance.utils.CalculateCostUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Component
@Profile("brazil")
@RequiredArgsConstructor
public class InsuranceCalcBrazilService implements InsuranceCalcService {
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
            default -> throw new IllegalArgumentException();
        }
        return price;
    }
}
