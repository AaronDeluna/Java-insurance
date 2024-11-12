package org.javaacademy.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static org.javaacademy.insurance.entity.InsuranceContractStatus.UNPAID;

/**
 * Страховой договор.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class InsuranceContract {
    private final String contractNumber;
    private final BigDecimal insurancePrice;
    private final BigDecimal insuredAmount;
    private final InsuranceCurrency insuranceCurrency;
    private final Client client;
    private final OperatingCountry operatingCountry;
    private final InsuranceType insuranceType;
    private InsuranceContractStatus insuranceContractStatus = UNPAID;

}
