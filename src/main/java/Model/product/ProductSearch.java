package Model.product;

import Model.http.SearchBuilder;
import Model.search.Condition;
import Model.search.Operator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ProductSearch implements SearchBuilder {

    @Override
    public List<Condition> buildSearch(HttpServletRequest request) {//prendo i parametri dalla form

        List<Condition> conditions = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            String value = request.getParameter(param);
            if(value != null && !value.isBlank()){
                switch (param){
                    case "name":
                        conditions.add(new Condition("nome", Operator.MATCH,value));
                        break;
                    case "categoryId":
                        conditions.add(new Condition("idCategoria", Operator.EQ,value)); //chiave esterna
                        break;
                    case "minPrice":
                        conditions.add(new Condition("prezzo", Operator.GT,value));
                        break;
                    case "maxPrice":
                        conditions.add(new Condition("prezzo", Operator.LT,value));
                        break;
                    default:
                        break;
                }
            }
        }
        return conditions;
    }
}
