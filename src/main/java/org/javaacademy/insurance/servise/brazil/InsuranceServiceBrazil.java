package org.javaacademy.insurance.servise.brazil;

import lombok.AllArgsConstructor;
import org.javaacademy.insurance.config.properties.InsuranceCalculationBrazilProperties;
import org.javaacademy.insurance.model.Client;
import org.javaacademy.insurance.model.InsuranceContract;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.exception.ContractNotFoundException;
import org.javaacademy.insurance.servise.InsuranceService;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.utils.ContractNumberGenerator;

import java.math.BigDecimal;

import static org.javaacademy.insurance.model.InsuranceContractStatus.PAID;

@AllArgsConstructor
public class InsuranceServiceBrazil implements InsuranceService {
    private InsuranceCalcBrazilService insuranceCalcBrazilService;
    private InsuranceCalculationBrazilProperties properties;
    private ContractNumberGenerator contractNumberGenerator;
    private Archive archive;

    @Override
    public InsuranceContract generateInsuranceProposal(BigDecimal insuredAmount, Client client,
                                                       InsuranceType insuranceType) {
        String contractNumber = contractNumberGenerator.generateContractNumber();
        BigDecimal insurancePrice = insuranceCalcBrazilService.calculateInsuranceCost(insuredAmount, insuranceType);
        return new InsuranceContract(contractNumber, insurancePrice,
                insuredAmount, properties.getInsuranceCurrency(),
                client, properties.getOperatingCountry(), insuranceType);
    }

    @Override
    public InsuranceContract payInsurance(String contractNumber) throws ContractNotFoundException {
        InsuranceContract contract = archive.findContractByNumber(contractNumber);
        contract.setInsuranceContractStatus(PAID);
        return contract;
    }
}
