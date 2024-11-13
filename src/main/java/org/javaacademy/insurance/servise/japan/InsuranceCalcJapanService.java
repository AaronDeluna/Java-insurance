package org.javaacademy.insurance.servise.japan;

import lombok.RequiredArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationJapanProperties;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.servise.InsuranceCalcService;
import org.javaacademy.insurance.utils.CalculateCostUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Component
@EnableConfigurationProperties(value = InsuranceCalculationJapanProperties.class)
@RequiredArgsConstructor
public class InsuranceCalcJapanService implements InsuranceCalcService {
    private final InsuranceCalculationJapanProperties properties;

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
