package org.javaacademy.insurance.servise.brazil;

import lombok.AllArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationBrazilProperties;
import org.javaacademy.insurance.model.Client;
import org.javaacademy.insurance.model.InsuranceContract;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.exception.ContractNotFoundException;
import org.javaacademy.insurance.servise.InsuranceService;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class InsuranceServiceBrazil implements InsuranceService {

    private InsuranceCalcBrazilService insuranceCalcBrazilService;
    private InsuranceCalculationBrazilProperties properties;

    @Override
    public InsuranceContract generateInsuranceProposal(BigDecimal insuredAmount, Client client,
                                                       InsuranceType insuranceType) {
        String contractNumber = ContractNumberGenerator.generateContractNumber();
        BigDecimal insurancePrice = insuranceCalcBrazilService.calculateInsuranceCost(insuredAmount, insuranceType);
        return new InsuranceContract(contractNumber, insurancePrice,
                insuredAmount, properties.getInsuranceCurrency(),
                client, properties.getOperatingCountry(), insuranceType);
    }

    @Override
    public void payInsurance(String contractNumber) throws ContractNotFoundException {

    }
}
