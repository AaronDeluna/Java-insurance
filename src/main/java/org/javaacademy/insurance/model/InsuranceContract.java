package org.javaacademy.insurance.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static org.javaacademy.insurance.model.InsuranceContractStatus.UNPAID;

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
