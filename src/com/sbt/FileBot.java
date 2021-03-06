package com.sbt;

import java.io.*;
import java.util.ArrayList;

public class FileBot {

    public static void main(String[] args) throws FileNotFoundException {
        readConsole("C:\\Projects\\SBT-Training\\answers.txt");
    }

    //МЕТОД ЧТЕНИЯ ДАННЫХ В ФАЙЛЕ
    public static ArrayList readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        ArrayList sb = new ArrayList();
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
        return sb;
    }

    //МЕТОД ЧТЕНИЯ С КОНСОЛИ
    public static String readConsole(String FileName) {
        BufferedReader br = null;
        try {
            ArrayList rf = readFile(FileName);
            System.out.println(rf.get(0));
            br = new BufferedReader(new InputStreamReader(System.in));
            boolean flag1 = true;
            boolean flag2 = true;
            while (flag1) {
                int i = 1 + (int) Math.floor(Math.random() * (rf.size() - 2));
                String input = br.readLine().toLowerCase();
                switch (input) {
                    case "goodbye": {
                        System.out.println(rf.get(rf.size() - 1));
                        flag1 = false;
                        break;
                    }
                    case "stop talking": {
                        while (flag2) {
                            String input2 = br.readLine().toLowerCase();
                            switch (input2) {
                                case "start talking": {
                                    flag2 = false;
                                    break;
                                }
                                case "goodbye": {
                                    System.out.println(rf.get(rf.size() - 1));
                                    flag1 = false;
                                    flag2 = false;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        System.out.println(rf.get(i));
                        if (input.contains("use file")) {
                            rf = readFile(input.substring(9, input.length()));
                            System.out.println(rf.get(0));
                        }
                    }
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
}