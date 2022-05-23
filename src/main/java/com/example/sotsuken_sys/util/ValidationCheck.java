package com.example.sotsuken_sys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {
    /**
     * nullチェックと文字数チェック
     * @param str
     * @return
     */
    public static boolean emptyCheck(String str){
        if(str == null || str.length() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 正規表現でチェック
     * @param str
     * @return
     */
    public static boolean strCheck(String str){
        Pattern p = Pattern.compile("^$|^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])[!-~]*");
        Matcher m = p.matcher("aA1!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
        Boolean result = !m.matches();
        return result;
    }
}
