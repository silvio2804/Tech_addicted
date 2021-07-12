package Model.discount;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class DiscountValidator {
    static RequestValidator validateForm(HttpServletRequest request, boolean update){
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("discountName", Pattern.compile("^\\w{4,20}$"),"Il nome deve avere lunghezza tra 4 e 20 caratteri");
        validator.assertInt("percentage","La percentuale deve essere un intero");
        if(update){
            validator.assertInt("id","Id deve essere un numero intero");
        }
        return validator;
    }
}