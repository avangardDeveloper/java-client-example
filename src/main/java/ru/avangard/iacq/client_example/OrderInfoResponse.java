package ru.avangard.iacq.client_example;

import lombok.Data;

@Data
public class OrderInfoResponse {
    private Long id;
    private Long statusCode;
    private String statusDesc;
    private String statusDate;
    private Long responseCode;
    private String responseMessage;

    public boolean isOk() {
        return responseCode != null && responseCode.equals(0L);
    }





}
