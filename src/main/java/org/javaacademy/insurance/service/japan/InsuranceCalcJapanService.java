package org.javaacademy.insurance.service.japan;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.service.AbstractInsuranceCalcService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("japan")
public class InsuranceCalcJapanService extends AbstractInsuranceCalcService {

    public InsuranceCalcJapanService(InsuranceCalculationProperties properties) {
        super(properties);
    }
}
