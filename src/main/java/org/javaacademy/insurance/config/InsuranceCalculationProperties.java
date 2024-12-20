package org.javaacademy.insurance.config;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.insurance.insurance_objects.InsuranceCurrency;
import org.javaacademy.insurance.insurance_objects.OperatingCountry;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class InsuranceCalculationProperties {
    private BigDecimal medicalCaseCoefficient;
    private BigDecimal theftServiceCost;
    private BigDecimal robberyCoefficient;
    private BigDecimal medicalServiceCost;
    private InsuranceCurrency insuranceCurrency;
    private OperatingCountry operatingCountry;

}
