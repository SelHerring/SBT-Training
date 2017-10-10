package com.company;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class FileBot {

    public static void main(String[] args) throws FileNotFoundException {
        readConsole("/Users/macbook/Projects/Bot/answers.txt");
    }

    //МЕТОД ЧТЕНИЯ ДАННЫХ В ФАЙЛЕ
    public static ArrayList readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        ArrayList sb = new ArrayList();

        exists(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    sb.add(s);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb; //toString();
    }

    //МЕТОД ЧТЕНИЯ С КОНСОЛИ
    public static String readConsole(String FileName) {
        BufferedReader br = null;
        try {
            ArrayList rf = readFile(FileName);
            System.out.println(rf.get(0));
            br = new BufferedReader(new InputStreamReader(System.in));
            boolean flag = true;
            while (flag) {
                int i = (int) Math.floor(Math.random() * (rf.size() - 1));
                String input = br.readLine();
                switch (input) {
                    case "Goodbye": {
                        System.out.println(rf.get(rf.size() - 1));
                        flag = false;
                        break;
                    }
                    case "Stop talking": {
                        break;
                    }
                    default:
                        System.out.println(rf.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return br.toString();
    }

    //МЕТОД ПРОВЕРКИ СУЩЕСТВОВАНИЯ ФАЙЛА
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}