package ru.avangard.iacq.client_example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AcqService {

    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();


    public  RegistrationOrderResponse registerOrder(RegistrationOrderRequest req) {
        String reqStr = gson.toJson(req);
        final String respStr = post("https://pay.avangard.ru/iacq/h2h/reg", reqStr);
        final RegistrationOrderResponse response = gson.fromJson(respStr, RegistrationOrderResponse.class);
        if (!response.isOk())
            throw new RuntimeException(response.getResponseCode() + " " + response.getResponseMessage());
        return response;
    }


    public  OrderInfoResponse orderInfo(OrderInfoRequest req) {
        String reqStr = gson.toJson(req);
        final String respStr = post("https://pay.avangard.ru/iacq/h2h/get_order_info", reqStr);
        final OrderInfoResponse response = gson.fromJson(respStr, OrderInfoResponse.class);
        if (!response.isOk())
            throw new RuntimeException(response.getResponseCode() + " " + response.getStatusDesc());
        return response;
    }


    public  RevertResponse revertOrder(RevertRequest req) {
        String reqStr = gson.toJson(req);
        final String respStr = post("https://pay.avangard.ru/iacq/h2h/reverse_order", reqStr);
        final RevertResponse response = gson.fromJson(respStr, RevertResponse.class);
        if (!response.isOk())
            throw new RuntimeException(response.getResponseCode() + " " + response.getResponseMessage());
        return response;
    }




    public static String post(String urlString, String body) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }
            int responseCode = conn.getResponseCode();
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            //System.out.println("Response Code: " + responseCode);
            //System.out.println("Response Body: " + response.toString());
            conn.disconnect();

            return response.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
