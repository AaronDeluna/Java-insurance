package org.javaacademy.insurance.servise.japan;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.servise.InsuranceCalcCountryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("japan")
public class InsuranceCalcJapanService extends InsuranceCalcCountryService {

    public InsuranceCalcJapanService(InsuranceCalculationProperties properties) {
        super(properties);
    }
}
