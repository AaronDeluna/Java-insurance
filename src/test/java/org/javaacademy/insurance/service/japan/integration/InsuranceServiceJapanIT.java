package org.javaacademy.insurance.service.japan.integration;

import lombok.extern.slf4j.Slf4j;
import org.javaacademy.insurance.model.Client;
import org.javaacademy.insurance.model.InsuranceContract;
import org.javaacademy.insurance.model.InsuranceContractStatus;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.service.japan.InsuranceCalcJapanService;
import org.javaacademy.insurance.service.japan.InsuranceServiceJapan;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.service.ContractNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.javaacademy.insurance.model.InsuranceContractStatus.PAID;
import static org.javaacademy.insurance.model.InsuranceContractStatus.UNPAID;
import static org.javaacademy.insurance.model.InsuranceCurrency.JPY;
import static org.javaacademy.insurance.model.InsuranceType.MEDICAL;
import static org.javaacademy.insurance.model.InsuranceType.THEFT;
import static org.javaacademy.insurance.model.OperatingCountry.JAPAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("japan")
@SpringBootTest
@Slf4j
class InsuranceServiceJapanIT {
    @Autowired
    private InsuranceServiceJapan insuranceServiceJapan;
    @MockBean
    private InsuranceCalcJapanService insuranceCalcJapanService;
    @MockBean
    private Archive archive;
    @MockBean
    private ContractNumberGenerator contractNumberGenerator;
    private final Client client = new Client(
            "Иванов", "Иван", "Иванович"
    );

    @Test
    @DisplayName("Успешное получение предложения по страховке от грабежа")
    void testSuccessfulRobberyInsuranceQuote() {
        String contractNumber = "001";
        BigDecimal insurancePrice = valueOf(20_000);
        BigDecimal insuredAmount = valueOf(1_000_000);
        InsuranceType insuranceType = THEFT;

        InsuranceContract expectedContract = new InsuranceContract(contractNumber,
                insurancePrice, insuredAmount, JPY, client, JAPAN, insuranceType
        );

        when(contractNumberGenerator.generateContractNumber()).thenReturn(contractNumber);
        when(insuranceCalcJapanService.calculateInsuranceCost(insuredAmount,
                insuranceType)).thenReturn(insurancePrice);

        InsuranceContract resultContract = insuranceServiceJapan.generateInsuranceProposal(
                insuredAmount, client, insuranceType);

        InsuranceContractStatus resultStatus = resultContract.getInsuranceContractStatus();

        assertEquals(expectedContract, resultContract);
        assertEquals(UNPAID, resultStatus);
    }

    @Test
    @DisplayName("Успешное получение предложения для мед страховки")
    void testSuccessfulInsuranceQuoteForTenMillion() {
        String contractNumber = "001";
        BigDecimal insurancePrice = valueOf(162_000);
        BigDecimal insuredAmount = valueOf(10_000_000);
        InsuranceType insuranceType = MEDICAL;

        InsuranceContract expectedContract = new InsuranceContract(contractNumber,
                insurancePrice, insuredAmount, JPY, client, JAPAN, insuranceType
        );

        when(archive.findContractByNumber(contractNumber)).thenReturn(expectedContract);
        when(insuranceCalcJapanService.calculateInsuranceCost(insuredAmount,
                insuranceType)).thenReturn(insurancePrice);

        InsuranceContract resultContract = archive.findContractByNumber(contractNumber);
        InsuranceContractStatus resultStatus = resultContract.getInsuranceContractStatus();

        BigDecimal resultInsurancePrice = insuranceCalcJapanService.calculateInsuranceCost(
                insuredAmount, insuranceType);

        assertEquals(expectedContract, resultContract);
        assertEquals(UNPAID, resultStatus);
        assertEquals(0, insurancePrice.compareTo(resultInsurancePrice));
    }


    @Test
    @DisplayName("Успешная оплата страховки")
    void testSuccessfulInsurancePayment() {
        String contractNumber = "001";
        BigDecimal insurancePrice = valueOf(165_000);
        BigDecimal insuredAmount = valueOf(10_000_000);

        InsuranceContract expectedContract = new InsuranceContract(
                contractNumber, insurancePrice,
                insuredAmount, JPY, client,
                JAPAN, MEDICAL
        );

        Mockito.when(archive.findContractByNumber(contractNumber)).thenReturn(expectedContract);

        InsuranceContract resultContract = insuranceServiceJapan.payInsurance(contractNumber);

        assertEquals(expectedContract, resultContract);
        assertEquals(PAID, resultContract.getInsuranceContractStatus());
    }

}