package org.javaacademy.insurance.servise.japan;

import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.servise.AbstractInsuranceServiceCountry;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("japan")
public class AbstractInsuranceServiceJapan extends AbstractInsuranceServiceCountry {

    public AbstractInsuranceServiceJapan(AbstractInsuranceCalcJapanService insuranceCalcJapanService,
                                         InsuranceCalculationProperties properties,
                                         ContractNumberGenerator contractNumberGenerator,
                                         Archive archive) {
        super(insuranceCalcJapanService, properties, contractNumberGenerator, archive);
    }
}
