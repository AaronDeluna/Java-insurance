package org.javaacademy.insurance.insurance_objects;

import lombok.Data;
import java.math.BigDecimal;

import static org.javaacademy.insurance.insurance_objects.InsuranceContractStatus.UNPAID;

/**
 * Страховой договор.
 */
@Data
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
