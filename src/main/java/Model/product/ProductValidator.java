package Model.product;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProductValidator {
    static RequestValidator validateForm(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("name", Pattern.compile("^\\w{4,30}$"),"Nome compreso fra i 4 e i 30 caratteri");
        validator.assertInt("categoryId","Categoria deve essere un intero");
        return validator;
    }
}
