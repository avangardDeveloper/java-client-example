package ru.avangard.iacq.client_example;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RegistrationOrderRequest {
    private String orderNumber;
    private BigDecimal amount;
    private String orderDescription;
    private Long shopId;
    private String shopPasswd;
    private String backUrl;
    private String language;

}
