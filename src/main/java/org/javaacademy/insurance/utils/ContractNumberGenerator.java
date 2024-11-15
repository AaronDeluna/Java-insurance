package org.javaacademy.insurance.utils;

import org.springframework.stereotype.Component;

/**
 * Генератор номеров договоров.
 */
@Component
public class ContractNumberGenerator {
    private long contractNumber = 0;

    /**
     * Генирация уникального номера для контракта.
     * @return вернет уникальный номер.
     */
    public String generateContractNumber() {
        return String.format("%03d", contractNumber++);
    }

}
