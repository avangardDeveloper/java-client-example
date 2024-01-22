package ru.avangard.iacq.client_example;

import lombok.Getter;

@Getter
public enum StatusCodes {
    NOT_FOUND(0, "Заказ не найден"),
    PROCESSING(1, "Обрабатывается"),
    REJECTED(2, "Отбракован"),
    FULFILLED(3, "Исполнен"),
    PARTIAL_RETURN(5, "Частичный возврат"),
    RETURN(6, "Возврат");

    private final int code;
    private final String description;

    StatusCodes(int code, String description) {
        this.code = code;
        this.description = description;
    }

}