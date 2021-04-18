package com.company;
import java.util.*;

public class Main {

    public static boolean checkBrackets(String in){
        int ocb = 0;
        for (int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '('){
                ocb++;
            }
            if(in.charAt(i) == ')'){
                ocb--;
            }
        }
        return ocb == 0;
    }

    public static String cropBrackets(String in){
        String result;

        return result;
    }

    public static boolean isKNF(String in){

        return true;
    }

    public static void main(String[] args) {
	    String input = "";
	    Scanner in = new Scanner(System.in);
	    int ch;
	    while (true) {
            System.out.println("1. Ввод");
            System.out.println("2. Проверить функцию");
            System.out.println("0. Выход /n");
            ch = Integer.parseInt(in.nextLine());
            switch (ch) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    input = in.nextLine();
                }
                case 2 -> {
                    if (!input.equals(""))
                        if (checkBrackets(input)) {
                            System.out.println("+");
                        } else {
                            System.out.println("Некорректное выражение!");
                            break;
                        }
                    if (isKNF(input)) {
                        System.out.println("Функция является КНФ /n");
                    } else {
                        System.out.println("Функция не является КНФ /n");
                    }
                }
            }
        }
    }
}
