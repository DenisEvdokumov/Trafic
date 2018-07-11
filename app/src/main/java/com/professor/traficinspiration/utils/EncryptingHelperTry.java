package com.professor.traficinspiration.utils;


import com.professor.traficinspiration.ApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EncryptingHelperTry {

    public Character[] symbols = {'e','s','z','x','5','k','o','3','6','c','q','r','i','f','g','9','m','a','y','0','j','1','t','4',
            'd','u','w','8','v','l','7','h','b','2','p','n'};


    public void generateKey() {

//        Random random = new Random();
//
//        // random number
//        int rand = random.nextInt(11) + 2;
//
//        // client paramKey
//        int clientKey = (int) (Math.pow(3, rand) % 17);
//
//
//        // !!!send client paramKey to server
//
//        // !!!receive server paramKey from server
//        //int serverParamKey = ApplicationContext.getMessageService().getServerKey(clientKey);
//
//        // result paramKey
//        int res = (int) (Math.pow(serverParamKey, rand) % 17);
//
//        // create key from symbol array
//        String key = "";
//        int arrayLength = symbols.length;
//        for (int i = 1; i < 11; i++) {
//            int index = (i * res) % arrayLength;
//
//            key += symbols[index];
//        }
//
//        // shuffle array
//        // ?save array to preferences?
//
//        List<Character> oldSymbols = new ArrayList<>();
//
//
//        oldSymbols.addAll(Arrays.asList(symbols));
//
//        List<Character> shuffledSymbols = new ArrayList<>();
//
//        for (int index = 0; oldSymbols.isEmpty(); index = (index + res) % oldSymbols.size()){
//            shuffledSymbols.add(oldSymbols.remove(index));
//        }
//
//        symbols = shuffledSymbols.toArray(symbols);
//
////        InputStream inputStream = new ByteArrayInputStream(symbols.toString().getBytes());
//

    }


    public String convertToBinaryString(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
//            binary.append(' ');
        }

        return binary.toString();
    }



    // метод для шифровки текста с помощью XOR
    public static byte[] encode(String secret, String key) {
        byte[] btxt = null;
        byte[] bkey = null;

        btxt = secret.getBytes();
        bkey = key.getBytes();

        byte[] result = new byte[secret.length()];

        for (int i = 0; i < btxt.length; i++) {
            result[i] = (byte) (btxt[i] ^ bkey[i % bkey.length]);
        }
        return result;
    }

    // метод для расшифровки текста
    public static String decode(byte[] secret, String key) {
        byte[] result = new byte[secret.length];
        byte[] bkey = key.getBytes();

        for (int i = 0; i < secret.length; i++) {
            result[i] = (byte) (secret[i] ^ bkey[i % bkey.length]);
        }
        return new String(result);
    }

}
