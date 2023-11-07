package validationWorkSpace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
    public static final Object lock = new Object();
    private static validation v = null ;
    public validation()
    {

    }
    public boolean isName (String str)
    {
        return str.matches("^[a-zA-Z][a-zA-Z0-9]*\\s*[a-zA-Z][a-zA-Z0-9]*");
    }
    public boolean isSubjectName(String str)
    {
        return str.matches("^[a-zA-Z]+\\s?[a-zA-Z]*");
    }
    public boolean isAge (String str)
    {
        return str.matches("\\d{1,2}");
    }
    public boolean isPassword(String str)
    {
        return str.matches("^[a-zA-Z0-9@]*\\s*[a-zA-Z0-9@]*");
    }
    public boolean isBlank(String str)
    {
        return str.equals("");
    }
    public boolean isNumber (String str)
    {
        return str.matches("^[1-9][0-9]{1,3}");
    }
    public boolean isDate (String str)
    {return str.matches("^0[1-9]/0[1-9]/20[2-9][0-9]$")
            || str.matches("^[1-2][0-9]/1[0-2]/20[2-9][0-9]$")
            ||str.matches("^[1-2][0-9]/0[1-9]/20[2-9][0-9]$")
            ||str.matches("^0[1-9]/1[0-2]/20[2-9][0-9]$")
            ||str.matches("^30/0[1-9]/20[2-9][0-9]$")
            ||str.matches("^30/1[0-2]/20[2-9][0-9]$");}
    public boolean isTime(String str)
    {
        return str.matches("0[0-9]:[0-5][0-9]$")||str.matches("1[0-2]:[0-5][0-9]$");
    }

    public static  validation getInstance (){
        if(v == null){
            synchronized (lock){
                if(v==null){
                    v=new validation();
                }
            }
        }
        return v ;
    }
}
