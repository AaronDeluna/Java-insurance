package org.javaacademy.insurance.servise;

import org.javaacademy.insurance.model.Client;
import org.javaacademy.insurance.model.InsuranceContract;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.exception.ContractNotFoundException;

import java.math.BigDecimal;

public interface InsuranceService {
    /**
     * Выдача предложения по страховке.
     * @param insuredAmount сумма покрытия.
     * @param client ФИО клиента.
     * @param insuranceType тип страховки.
     * @return страховку.
     */
    InsuranceContract generateInsuranceProposal(BigDecimal insuredAmount,
                                                Client client,
                                                InsuranceType insuranceType);

    /**
     * Оплата страховки.
     * @param contractNumber Номер страховки.
     * @throws ContractNotFoundException если страховка с таким глмером не найдена.
     */
    void payInsurance(String contractNumber) throws ContractNotFoundException;
}
