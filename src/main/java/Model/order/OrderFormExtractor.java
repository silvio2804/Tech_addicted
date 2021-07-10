package Model.order;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class OrderFormExtractor implements FormExtractor<Order> {
    @Override
    public Order extract(HttpServletRequest request, boolean update) {
        Order order = new Order();
        if(update){
            order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
        }
        Date date = new Date();
        order.setOrderDate(date);
        order.setPayment(request.getParameter("payement"));
        return order;
    }
}
