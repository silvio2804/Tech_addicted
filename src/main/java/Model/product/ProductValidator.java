package Model.product;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProductValidator {
    static RequestValidator validateForm(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("name", Pattern.compile("^[a-zA-Z 0-9 ]{4,30}"),"Nome compreso fra i 4 e i 30 caratteri");
        validator.assertDouble("price","Il prezzo deve essere espresso in decimali con un punto");
        validator.assertInt("categoryId","Categoria deve essere un intero");
        return validator;
    }
}
