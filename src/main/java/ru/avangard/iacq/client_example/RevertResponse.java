package ru.avangard.iacq.client_example;

import lombok.Data;

@Data
public class RevertResponse {
    Long responseCode;
    String responseMessage;

    public boolean isOk() {
        return responseCode != null && responseCode.equals(0L);
    }
}
