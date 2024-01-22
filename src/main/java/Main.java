import ru.avangard.iacq.client_example.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        //Все суммы в копейках

        Long shopId = 1113L;          //1113 это тестовый магазин, оплаты по нему не будут исполнены, но статусы меняются
        String pwd = "";   //Пароль для тестового магазина можно получить по почте в отделе эквайринга

        final AcqService acqService = new AcqService();

        final RegistrationOrderRequest registrationOrderRequest = new RegistrationOrderRequest("555", BigDecimal.valueOf(1000), "some desc", shopId, pwd, "https://some.url.to/redirect/after/payment", "RU");
        final RegistrationOrderResponse registrationOrderResponse = acqService.registerOrder(registrationOrderRequest);
        System.out.println(registrationOrderResponse);
        System.out.println(registrationOrderResponse.isOk());

        final OrderInfoRequest orderInfoRequest = new OrderInfoRequest(registrationOrderResponse.getTicket(), shopId, pwd);
        final OrderInfoResponse orderInfoResponse = acqService.orderInfo(orderInfoRequest);
        System.out.println(orderInfoResponse);
        System.out.println(orderInfoResponse.isOk()); //responseCode говорит о статусе исполнения запроса, statusCode о состоянии заказа
        System.out.println("Статус заказа " + orderInfoResponse.getStatusCode() + " " + orderInfoResponse.getStatusDesc()); //возможные статусы в StatusCodes


        //Для возвратов по картам revertResponse синхронно вернет статус операции. 0 значит возврат исполнен
        //Для тестового магазина не будет успешных возвратов, так как не исполняются авторизации платежей
        final RevertRequest revertRequest = new RevertRequest(registrationOrderResponse.getTicket(), shopId, pwd, BigDecimal.valueOf(500));
        final RevertResponse revertResponse = acqService.revertOrder(revertRequest);
        System.out.println(revertResponse);


    }


}
