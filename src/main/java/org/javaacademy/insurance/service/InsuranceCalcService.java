package org.javaacademy.insurance.service;


import org.javaacademy.insurance.insurance_objects.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    /**
     * Расчет стоимоти страховки.
     * @param insuredAmount сумма покрытия.
     * @param insuranceType тип старховки.
     * @return вернет расчитанную стоимость.
     */
    BigDecimal calculateInsuranceCost(BigDecimal insuredAmount,
                                      InsuranceType insuranceType);
}
