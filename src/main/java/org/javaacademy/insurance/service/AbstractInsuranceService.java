package org.javaacademy.insurance.service;

import lombok.AllArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationProperties;
import org.javaacademy.insurance.exception.ContractNotFoundException;
import org.javaacademy.insurance.model.Client;
import org.javaacademy.insurance.model.InsuranceContract;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.storage.Archive;

import java.math.BigDecimal;

import static org.javaacademy.insurance.model.InsuranceContractStatus.PAID;

@AllArgsConstructor
public abstract class AbstractInsuranceService implements InsuranceService {
    private InsuranceCalcService insuranceCalcService;
    private InsuranceCalculationProperties properties;
    private ContractNumberGenerator contractNumberGenerator;
    private Archive archive;

    @Override
    public InsuranceContract generateInsuranceProposal(BigDecimal insuredAmount, Client client,
                                                       InsuranceType insuranceType) {
        String contractNumber = contractNumberGenerator.generateContractNumber();
        BigDecimal insurancePrice = insuranceCalcService.calculateInsuranceCost(insuredAmount, insuranceType);
        return new InsuranceContract(contractNumber, insurancePrice, insuredAmount,
                properties.getInsuranceCurrency(), client,
                properties.getOperatingCountry(), insuranceType);
    }

    @Override
    public InsuranceContract payInsurance(String contractNumber) throws ContractNotFoundException {
        InsuranceContract contract = archive.findContractByNumber(contractNumber);
        contract.setInsuranceContractStatus(PAID);
        return contract;
    }

}
