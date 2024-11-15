package org.javaacademy.insurance.servise.brazil;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.servise.AbstractInsuranceServiceCountry;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("brazil")
public class AbstractInsuranceServiceBrazil extends AbstractInsuranceServiceCountry {

    public AbstractInsuranceServiceBrazil(AbstractInsuranceCalcBrazilService insuranceCalcBrazilService,
                                          InsuranceCalculationProperties properties,
                                          ContractNumberGenerator contractNumberGenerator,
                                          Archive archive) {
        super(insuranceCalcBrazilService, properties, contractNumberGenerator, archive);
    }
}
