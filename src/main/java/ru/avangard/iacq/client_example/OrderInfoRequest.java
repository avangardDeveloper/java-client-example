package ru.avangard.iacq.client_example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderInfoRequest {
    private String ticket;
    private Long shopId;
    private String shopPasswd;


}
