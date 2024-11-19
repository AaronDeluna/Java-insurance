package org.javaacademy.insurance.service.brazil;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.service.AbstractInsuranceService;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.service.ContractNumberGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("brazil")
public class InsuranceServiceBrazil extends AbstractInsuranceService {

    public InsuranceServiceBrazil(InsuranceCalcBrazilService insuranceCalcBrazilService,
                                  InsuranceCalculationProperties properties,
                                  ContractNumberGenerator contractNumberGenerator,
                                  Archive archive) {
        super(insuranceCalcBrazilService, properties, contractNumberGenerator, archive);
    }
}
