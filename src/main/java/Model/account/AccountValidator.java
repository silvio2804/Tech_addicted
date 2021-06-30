package Model.account;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class AccountValidator {

    public static RequestValidator validateSignin(HttpServletRequest request){
        RequestValidator requestValidator = new RequestValidator(request);
        Enumeration<String> params = request.getParameterNames();
        String email = "email";
        while (params.hasMoreElements())
            if(email.compareToIgnoreCase(params.nextElement()) == 0)
                requestValidator.assertEmail(email, "email non valida");
        return requestValidator;
    }

    public static RequestValidator validateForm(HttpServletRequest request, boolean b) {
        RequestValidator requestValidator = new RequestValidator(request);
        return requestValidator;
    }
}
