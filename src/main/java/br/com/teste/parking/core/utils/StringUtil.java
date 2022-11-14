package br.com.teste.parking.core.utils;

import java.util.Random;

public class StringUtil {

    public static String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

}
