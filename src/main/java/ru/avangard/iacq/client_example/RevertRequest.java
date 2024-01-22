package ru.avangard.iacq.client_example;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RevertRequest {
    private String ticket;
    private Long shopId;
    private String shopPasswd;
    private BigDecimal amount;
}
