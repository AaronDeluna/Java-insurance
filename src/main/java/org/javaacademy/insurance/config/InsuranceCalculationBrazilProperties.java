package org.javaacademy.insurance.config;

import lombok.Data;
import org.javaacademy.insurance.model.InsuranceCurrency;
import org.javaacademy.insurance.model.OperatingCountry;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(value = "application-brazil.yaml")
@Data
public class InsuranceCalculationBrazilProperties {
    private BigDecimal medicalCaseCoefficient;
    private BigDecimal theftServiceCost;
    private BigDecimal robberyCoefficient;
    private BigDecimal medicalServiceCost;
    private InsuranceCurrency insuranceCurrency;
    private OperatingCountry operatingCountry;

}
