package Model.search;

public enum Operator {
    GT, LT, EQ, NE, GE, LE, MATCH;

    public String toString(){
        String operator = "";
        switch(this){
            case LT :
                operator =" < ";
            break;
            case EQ :
                operator =" = ";
                break;
            case GE :
                operator =" >= ";
                break;
            case NE :
                operator = " != ";
                break;
            case GT : ;
                operator = " > ";
                break;
            case LE :
                operator = " <= ";
                break;
            case MATCH :
                operator = " LIKE ";
                break;
        }
        return operator;
    }
}
