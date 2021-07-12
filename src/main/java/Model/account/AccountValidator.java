package Model.account;

import Model.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class AccountValidator {

    public static RequestValidator validateSignin(HttpServletRequest request){
        RequestValidator requestValidator = new RequestValidator(request);
        requestValidator.assertEmail("email", "email non valida");
       //requestValidator.assertPassword("password","password non valida");
        return requestValidator;
    }

    public static RequestValidator validateForm(HttpServletRequest request, boolean update) {
        RequestValidator requestValidator = new RequestValidator(request);
        if(update){
            requestValidator.assertInt(request.getParameter("id"),"id deve essere un intero");
        }
        requestValidator.assertMatch("name", Pattern.compile("^[a-zA-Z ]{1,40}$"),"Il nome deve avere lunghezza tra 1 e 40 caratteri");
        requestValidator.assertMatch("lastName", Pattern.compile("[a-zA-Z ]{1,40}$"),"Il cognome deve avere lunghezza tra 1 e 40 caratteri");
        requestValidator.assertEmail("email","email non valida o gia esistente");
        requestValidator.assertMatch("street", Pattern.compile("^[a-zA-Z ]{1,40}$"),"Strada non può essere nullo");
        requestValidator.assertMatch("city", Pattern.compile("^[a-zA-Z ]{1,40}$"),"Citta non può essere nullo");
        requestValidator.assertInt("houseNumber","Numero civico deve essere un intero");
        return requestValidator;
    }
}
