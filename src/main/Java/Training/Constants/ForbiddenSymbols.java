package Training.Constants;

/**
 * Created by Администратор on 08.06.2017.
 */
public enum ForbiddenSymbols {
    COMMA(","),
    OPEN_PAREN("("),
    CLOSE_PAREN(")"),
    DOG("@"),
    WOW("!"),
    CAGE("#"),
    DOLA("$"),
    PERCENT("%"),
    ROOF("^"),
    STAR("*"),
    LEFT("<"),
    RIGHT(">"),
    Q("?"),
    AND("&");


    private String value;
    private ForbiddenSymbols(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value;
    }
}
