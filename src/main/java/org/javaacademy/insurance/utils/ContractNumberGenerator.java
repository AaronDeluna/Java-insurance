package org.javaacademy.insurance.utils;

import lombok.experimental.UtilityClass;

/**
 * Генератор номеров договоров.
 */
@UtilityClass
public class ContractNumberGenerator {
    private long contractNumber = 0;

    /**
     * Генирация уникального номера для контракта.
     * @return вернет уникальный номер.
     */
    public String generateContractNumber() {
        return String.valueOf(contractNumber++);
    }

}
