package ru.avangard.iacq.client_example;


import lombok.Data;

@Data
public class RegistrationOrderResponse {
    private Long id;
    private String ticket;
    private String okCode;
    private String failureCode;
    private Long responseCode;
    private String responseMessage;

    public  boolean isOk() {
        return responseCode != null && responseCode.equals(0L);
    }

}
