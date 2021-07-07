package Model.account;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

public class AccountFormExtractor implements FormExtractor<Account> {

    @Override
    public Account extract(HttpServletRequest request, boolean update) {
        Account formAccount = new Account();
        if(update){
            formAccount.setId(Integer.parseInt(request.getParameter("id")));
        }
        formAccount.setName(request.getParameter("name"));
        formAccount.setLastName(request.getParameter("lastName"));
        //formAccount.setDate();
        Date date = (Date)  request.getAttribute("date");

        formAccount.setEmail(request.getParameter("email"));
        formAccount.setStreet(request.getParameter("street"));
        formAccount.setHouseNumber(Integer.parseInt("houseNumber"));
        formAccount.setCity(request.getParameter("city"));
        formAccount.setGender("gender");
        return formAccount;
    }
}
