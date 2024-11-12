package org.javaacademy.insurance.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class CalculateCostUtils {

    /**
     * Расчет стоимости.
     * @param insuredAmount сумма покрытия
     * @param coefficient коифецент страховки
     * @param serviceCost стоимость услуги
     * @return стоимость
     */
    public BigDecimal calculateCost(BigDecimal insuredAmount, BigDecimal coefficient,
                                     BigDecimal serviceCost) {
        return insuredAmount.multiply(coefficient).add(serviceCost);
    }
}
