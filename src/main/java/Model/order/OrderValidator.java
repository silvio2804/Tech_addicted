package Model.order;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class OrderValidator {
    static RequestValidator validateForm(HttpServletRequest request, boolean update){
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("categoryName", Pattern.compile("^\\w{4,20}$"),"Il nome deve avere lunghezza tra 4 e 20 caratteri");
        validator.assertMatch("payment",Pattern.compile("^\\w{4,20}$"),"pagamento deve avere lunghezza tra 4 e 20 caratteri");
        if(update){
            validator.assertInt("orderId","Id deve essere un numero intero");
        }
        return validator;
    }
}
