package application.validation;
import java.util.regex.Pattern;

public class Util {
    public static boolean validLogin(String login){
        if(login.length() > 3)
            if(Pattern.matches("\\w+\\.?", login) && !(login.contains("_")))
                return true;
        return false;
    }
}