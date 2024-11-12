package org.javaacademy.insurance.servise;


import org.javaacademy.insurance.entity.InsuranceType;

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
