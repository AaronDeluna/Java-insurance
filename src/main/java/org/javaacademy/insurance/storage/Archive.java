package org.javaacademy.insurance.storage;

import lombok.Getter;
import org.javaacademy.insurance.exception.ContractNotFoundException;
import org.javaacademy.insurance.insurance_objects.InsuranceContract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Архив.
 */
@Component
@Getter
public class Archive {
    private final Map<String, InsuranceContract> insuranceContractStorage = new HashMap<>();

    public void add(String contractNumber, InsuranceContract contract) {
        insuranceContractStorage.put(contractNumber, contract);
    }

    public InsuranceContract findContractByNumber(String contractNumber) throws ContractNotFoundException {
        return Optional.ofNullable(insuranceContractStorage.get(contractNumber))
                .orElseThrow(ContractNotFoundException::new);
    }

}
