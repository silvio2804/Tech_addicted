package Model.tag;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class TagValidator {
    static RequestValidator validateForm(HttpServletRequest request, boolean update){
        RequestValidator validator = new RequestValidator(request);
        if(update){
            validator.assertInt("tagId","Id tag deve essere un intero");
        }
        validator.assertMatch("word", Pattern.compile("^[a-zA-Z 0-9 ]{1,30}$"),"La parola deve essere compresa fra i 2 e i 30 caratteri");
        return validator;
    }
}
