package org.javaacademy.insurance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Клиент.
 */
@Getter
@AllArgsConstructor
public class Client {
    private String surname;
    private String name;
    private String patronymic;

}
