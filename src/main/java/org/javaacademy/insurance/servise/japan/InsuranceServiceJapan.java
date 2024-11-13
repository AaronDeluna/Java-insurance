package org.javaacademy.insurance.servise.japan;

import lombok.AllArgsConstructor;
import org.javaacademy.insurance.config.InsuranceCalculationJapanProperties;
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
public class InsuranceServiceJapan implements InsuranceService {

    private InsuranceCalcJapanService insuranceCalcJapanService;
    private InsuranceCalculationJapanProperties properties;

    @Override
    public InsuranceContract generateInsuranceProposal(BigDecimal insuredAmount, Client client,
                                                       InsuranceType insuranceType) {
        String contractNumber = ContractNumberGenerator.generateContractNumber();
        BigDecimal insurancePrice = insuranceCalcJapanService.calculateInsuranceCost(insuredAmount, insuranceType);
        return new InsuranceContract(contractNumber, insurancePrice, insuredAmount,
                properties.getInsuranceCurrency(), client,
                properties.getOperatingCountry(), insuranceType);
    }

    @Override
    public void payInsurance(String contractNumber) throws ContractNotFoundException {

    }
}
