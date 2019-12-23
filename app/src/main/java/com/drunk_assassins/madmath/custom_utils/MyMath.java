package com.drunk_assassins.madmath.custom_utils;

import java.util.Random;

public class MyMath {

    public static int randInt(int min, int max) {
        Random var = new Random();
        return var.nextInt((max - min) + 1) + min;
    }

    public static int calculate(int v1, int v2, String op) {
        int result = 0;

        switch(op) {
            case "*":
                result = v1*v2;
                break;
            case "+":
                result = v1+v2;
                break;
            case "-":
                result = v1-v2;
                break;
        }

        return result;
    }


}
