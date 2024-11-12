package org.javaacademy.insurance.storage;

import lombok.Getter;
import org.javaacademy.insurance.entity.InsuranceContract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Архив.
 */
@Component
@Getter
public class Archive {
    private Map<String, InsuranceContract> InsuranceContractStorage = new HashMap<>();

}
