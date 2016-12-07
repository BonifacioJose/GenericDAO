package br.com.twsistemas.genericdao.util;

import java.util.Arrays;

/**
 *
 * @author José
 */
public abstract class ArrayUtil {

    public static String arrayToString(String[] array) {
        String newArray = Arrays.toString(array).replace("[", "").replace("]", "");
        return newArray;
    }

    public static String[] replaceArrayValues(String[] array, String newValue) {
        String[] newArray = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = newValue;
        }
        return newArray;
    }

}
