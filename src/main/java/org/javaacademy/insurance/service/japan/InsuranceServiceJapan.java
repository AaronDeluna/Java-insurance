package org.javaacademy.insurance.service.japan;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.service.AbstractInsuranceService;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("japan")
public class InsuranceServiceJapan extends AbstractInsuranceService {

    public InsuranceServiceJapan(InsuranceCalcJapanService insuranceCalcJapanService,
                                 InsuranceCalculationProperties properties,
                                 ContractNumberGenerator contractNumberGenerator,
                                 Archive archive) {
        super(insuranceCalcJapanService, properties, contractNumberGenerator, archive);
    }
}
