package org.javaacademy.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Клиент.
 */
@Getter
@Setter
@AllArgsConstructor
public class Client {
    private String surname;
    private String name;
    private String patronymic;
}
