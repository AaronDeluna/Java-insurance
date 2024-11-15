package org.javaacademy.insurance.service.brazil;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.service.AbstractInsuranceCalcService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("brazil")
public class InsuranceCalcBrazilService extends AbstractInsuranceCalcService {

    public InsuranceCalcBrazilService(InsuranceCalculationProperties properties) {
        super(properties);
    }
}
