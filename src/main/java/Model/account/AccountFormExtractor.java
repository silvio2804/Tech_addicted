package Model.account;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AccountFormExtractor implements FormExtractor<Account> {

    @Override
    public Account extract(HttpServletRequest request, boolean update) {
        Account formAccount = new Account();
        if(update){
            formAccount.setId(Integer.parseInt(request.getParameter("id")));
        }
        String name = (String) request.getAttribute("name");
        String lastName = (String) request.getAttribute("lastName");
        Date date = (Date)  request.getAttribute("date");
        String email = (String) request.getAttribute("email");
        String via = (String) request.getAttribute("via");
        String numeroCivico = (String) request.getAttribute("numero");
        String citta = (String) request.getAttribute("citta");
        String password = (String) request.getAttribute("password");
        String sesso = (String) request.getAttribute("sesso");
        return formAccount;
    }
}
