package org.javaacademy.insurance.servise.brazil;

import org.javaacademy.insurance.config.InsuranceCalculationBrazilProperties;
import org.javaacademy.insurance.entity.InsuranceType;
import org.javaacademy.insurance.servise.InsuranceCalcService;
import org.javaacademy.insurance.utils.CalculateCostUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Component
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    private InsuranceCalculationBrazilProperties properties;
    private BigDecimal price = ZERO;

    /**
     * Расчет стоимости страховки в Японии.
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
