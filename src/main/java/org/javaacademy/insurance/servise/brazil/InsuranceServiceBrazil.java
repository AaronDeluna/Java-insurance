package org.javaacademy.insurance.servise.brazil;

import lombok.AllArgsConstructor;
import org.javaacademy.insurance.entity.Client;
import org.javaacademy.insurance.entity.InsuranceContract;
import org.javaacademy.insurance.entity.InsuranceType;
import org.javaacademy.insurance.exception.ContractNotFoundException;
import org.javaacademy.insurance.servise.InsuranceService;
import org.javaacademy.insurance.utils.ContractNumberGenerator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.javaacademy.insurance.entity.InsuranceCurrency.ZAR;
import static org.javaacademy.insurance.entity.OperatingCountry.BRAZIL;

@Component
@AllArgsConstructor
public class InsuranceServiceBrazil implements InsuranceService {

    private InsuranceCalcBrazilService insuranceCalcBrazilService;


    @Override
    public InsuranceContract generateInsuranceProposal(BigDecimal insuredAmount, Client client,
                                                       InsuranceType insuranceType) {
        String contractNumber = ContractNumberGenerator.generateContractNumber();
        BigDecimal insurancePrice = insuranceCalcBrazilService.calculateInsuranceCost(insuredAmount, insuranceType);
        return new InsuranceContract(contractNumber, insurancePrice, insuredAmount,
                ZAR, client, BRAZIL, insuranceType);
    }

    @Override
    public void payInsurance(String contractNumber) throws ContractNotFoundException {

    }
}
