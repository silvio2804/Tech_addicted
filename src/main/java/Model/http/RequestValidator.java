package Model.http;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/*
* Classe utilizzabile per la validazione dei dati, accumulazione degli errori
* */
public class RequestValidator {

    private final List<String> errors;
    private final HttpServletRequest request;
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^[+-]?([0-9]*[.])?[0-9]+$");

    public RequestValidator(HttpServletRequest request){
        this.errors = new ArrayList<>();
        this.request = request;
    }

    public boolean hasErrors(){return !errors.isEmpty();}

    public List<String> getError(){return errors;}

    private boolean gatherError(boolean condition, String message){
        if(condition){
            return true;
        }
        else {
            errors.add(message);
            return false;
        }
    }

    private boolean required(String value){return value != null && !value.isBlank();}//controlla se un campo è obbligatorio,con spazi bianchi o null

    public boolean assertMatch(String value, Pattern regexp, String msg){//value è il noe del campo da analizzare
        String param = request.getParameter(value);
        boolean condition = required(param) && regexp.matcher(param).matches();
        return gatherError(condition,msg);
    }

    public boolean assertInt(String value, String msg){
        return assertMatch(value,INT_PATTERN,msg);
    }

    public boolean assertDouble(String value, String msg){
        return assertMatch(value,DOUBLE_PATTERN,msg);
    }

    public boolean assertEmail(String value, String msg){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
        return assertMatch(value,pattern, msg);
    }

    public boolean assertPassword(String value, String msg){ //ipotetica registrazione ??
        Pattern pattern = Pattern.compile("^[A-z0-9\\.\\+_-]+@[A-z0-9]+\\.[A-z]{2,6}$");
        return assertMatch(value,pattern, msg);
    }

    public boolean assertInts(String values, String msg){ //valida un array di id, ad esempio per la valdiazione di un ordine,gli id dei prodotti siano tutti interi
        String[] params = request.getParameterValues(values);
        boolean allInt = Arrays.stream(params).allMatch(param -> INT_PATTERN.matcher(param).matches());
        return gatherError(allInt,msg);
    }
    public boolean assertSize(String first, String second, String msg){ //controllare che due liste abbiano la stessa lunghezza
        String[] firstList = request.getParameterValues(first);
        String[] secondList = request.getParameterValues(second);
        return gatherError(firstList.length == secondList.length, msg);
    }

}
