package com.jackson.common.api.function;


import java.util.Scanner;

/**
 * Create by: Jackson
 */
public class InputManager {

    public String getInput(){
        String read;
        Scanner scan = new Scanner(System.in);
        while ((read = scan.nextLine()) != null) {
            return read;
        }
        return null;
    }

}
