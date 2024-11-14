package org.javaacademy.insurance.config.properties;

import lombok.Data;
import org.javaacademy.insurance.model.InsuranceCurrency;
import org.javaacademy.insurance.model.OperatingCountry;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "japan.data")
@Data
public class InsuranceCalculationJapanProperties {
    private BigDecimal medicalCaseCoefficient;
    private BigDecimal theftServiceCost;
    private BigDecimal robberyCoefficient;
    private BigDecimal medicalServiceCost;
    private InsuranceCurrency insuranceCurrency;
    private OperatingCountry operatingCountry;

}
