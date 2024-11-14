package org.javaacademy.insurance.config;

import lombok.RequiredArgsConstructor;
import org.javaacademy.insurance.config.properties.InsuranceCalculationBrazilProperties;
import org.javaacademy.insurance.servise.brazil.InsuranceCalcBrazilService;
import org.javaacademy.insurance.servise.brazil.InsuranceServiceBrazil;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("brazil_service")
@EnableConfigurationProperties(value = InsuranceCalculationBrazilProperties.class)
@RequiredArgsConstructor
public class InsuranceCalcBrazilServiceConfig {
    private final InsuranceCalculationBrazilProperties properties;

    @Bean
    public InsuranceCalcBrazilService insuranceCalcBrazilService() {
        return new InsuranceCalcBrazilService(properties);
    }

    @Bean
    public Archive archive() {
        return new Archive();
    }

    @Bean
    public ContractNumberGenerator contractNumberGenerator() {
        return new ContractNumberGenerator();
    }

    @Bean
    public InsuranceServiceBrazil insuranceServiceBrazil(
            InsuranceCalcBrazilService insuranceCalcBrazilService,
            ContractNumberGenerator contractNumberGenerator, Archive archive) {
        return new InsuranceServiceBrazil(insuranceCalcBrazilService, properties,
                contractNumberGenerator, archive);
    }
}
