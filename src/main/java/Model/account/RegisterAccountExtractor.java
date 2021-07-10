package Model.account;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class RegisterAccountExtractor implements FormExtractor<Account> {
    @Override
    public Account extract(HttpServletRequest request, boolean admin) {
        Account regAccount = new Account();
        regAccount.setName(request.getParameter("name"));
        regAccount.setLastName(request.getParameter("lastName"));
        regAccount.setEmail(request.getParameter("email"));
        regAccount.setDate(LocalDate.parse(request.getParameter("dataNa")));
        regAccount.setAdmin(admin);
        return regAccount;
    }
}
