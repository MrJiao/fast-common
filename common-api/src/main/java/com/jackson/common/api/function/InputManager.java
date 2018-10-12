package com.jackson.common.api.function;


import com.jackson.common.api.utils.L;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Create by: Jackson
 */
public class InputManager {

    public static String getInput(String ... chooseStr) {
        Scanner scan = new Scanner(System.in);
        String read = null;
        while (true) {
            read = scan.nextLine();
            if (StringUtils.isEmpty(read))
                continue;

            if(!isMatch(read,chooseStr)){
                printMust(chooseStr);
                continue;
            }
            break;
        }
        return read;
    }

    private static boolean isMatch(String input,String...msg){
        boolean match = false;
        if(msg==null || msg.length==0)return true;
        for (String s : msg) {
            if(StringUtils.equalsIgnoreCase(input,s)){
                match=true;
                break;
            }
        }
        return match;
    }


    private static void printMust(String... msg){
        String temp="";
        for (String s : msg) {
            temp +=s+" ";
        }
        L.d("你输错啦，只能输入",temp);
    }

}
