package org.javaacademy.insurance.service.japan.integration;

import org.javaacademy.insurance.insurance_objects.Client;
import org.javaacademy.insurance.insurance_objects.InsuranceContract;
import org.javaacademy.insurance.insurance_objects.InsuranceContractStatus;
import org.javaacademy.insurance.insurance_objects.InsuranceType;
import org.javaacademy.insurance.service.japan.InsuranceCalcJapanService;
import org.javaacademy.insurance.service.japan.InsuranceServiceJapan;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.insurance.service.ContractNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.javaacademy.insurance.insurance_objects.InsuranceContractStatus.PAID;
import static org.javaacademy.insurance.insurance_objects.InsuranceContractStatus.UNPAID;
import static org.javaacademy.insurance.insurance_objects.InsuranceCurrency.JPY;
import static org.javaacademy.insurance.insurance_objects.InsuranceType.MEDICAL;
import static org.javaacademy.insurance.insurance_objects.InsuranceType.THEFT;
import static org.javaacademy.insurance.insurance_objects.OperatingCountry.JAPAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("japan")
@SpringBootTest
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

        when(contractNumberGenerator.generateContractNumber()).thenReturn(contractNumber);
        when(insuranceCalcJapanService.calculateInsuranceCost(insuredAmount, insuranceType)).thenReturn(insurancePrice);
        when(archive.findContractByNumber(contractNumber)).thenReturn(expectedContract);

        InsuranceContract resultContract = archive.findContractByNumber(contractNumber);
        InsuranceContractStatus resultContractStatus = resultContract.getInsuranceContractStatus();

        assertEquals(expectedContract, resultContract);
        assertEquals(UNPAID, resultContractStatus);
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

        when(archive.findContractByNumber(contractNumber)).thenReturn(expectedContract);

        InsuranceContract resultContract = insuranceServiceJapan.payInsurance(contractNumber);

        assertEquals(expectedContract, resultContract);
        assertEquals(PAID, resultContract.getInsuranceContractStatus());
    }

}