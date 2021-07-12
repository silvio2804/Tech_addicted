package Model.cart;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CartValidator{

    static RequestValidator validateProduct(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("categoryName", Pattern.compile("^\\w{4,20}$"),"Il nome deve avere lunghezza tra 4 e 20 caratteri");
        return validator;
    }
}
