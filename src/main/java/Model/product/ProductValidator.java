package Model.product;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProductValidator {

    static RequestValidator validateForm(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("name", Pattern.compile("^\\w{5,30}$"),"nome compreso fra i 5 e i 30 caratteri");
        validator.assertInt("CategoryId","Categoria deve essere un intero");
        validator.assertDouble("price","Categoria deve essere un intero");
        return validator;
    }
}
