package application.validation;
import java.util.regex.Pattern;

public class Util {
    private static final String NUMBER_PREFIX_UA = "380";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validLogin(String login){
        if(login.length() > 3)
            if(Pattern.matches("\\w+\\.?", login) && !(login.contains("_")))
                return true;
        return false;
    }

    public static boolean validEmain(String emain){ return Pattern.compile(EMAIL_PATTERN).matcher(emain).matches(); }

    public static boolean validTelephoneNumber(String number){
        if(number.startsWith(NUMBER_PREFIX_UA))
            if((Pattern.matches("\\d+", number)))
                if(number.length() == 12)
                    return true;
        return false;
    }
}