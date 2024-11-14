package org.javaacademy.insurance.config;

import lombok.AllArgsConstructor;
import org.javaacademy.insurance.config.properties.InsuranceCalculationJapanProperties;
import org.javaacademy.insurance.servise.japan.InsuranceCalcJapanService;
import org.javaacademy.insurance.servise.japan.InsuranceServiceJapan;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("japan_service")
@EnableConfigurationProperties(value = InsuranceCalculationJapanProperties.class)
@AllArgsConstructor
public class InsuranceCalcJapanServiceConfig {
    private InsuranceCalculationJapanProperties properties;

    @Bean
    public InsuranceCalcJapanService insuranceCalcJapanService() {
        return new InsuranceCalcJapanService(properties);
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
    public InsuranceServiceJapan insuranceServiceJapan(
            InsuranceCalcJapanService insuranceCalcJapanService,
            ContractNumberGenerator contractNumberGenerator, Archive archive) {
        return new InsuranceServiceJapan(insuranceCalcJapanService, properties, contractNumberGenerator, archive);
    }
}
