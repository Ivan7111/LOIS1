//////////////////////////////////////////////////////////////////////////////////////
// Лабораторная работа 1 по дисциплине ЛОИС
// Выполнена студентом группы 821701 БГУИР Грицелем Иваном Александровичем
//

package com.company;
import java.util.*;

public class Main {

    public static int checkSyntax(String in){
        int ocb = 0;
        int symbolStreak = 0;
        int operandStreak = 0;
        int operandSymbolNumber = 0;
        int symbolQuantity = 0;
        boolean check;
        for (int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '('){ // проверка скобок
                if(i != 0 && in.charAt(i - 1) == '!'){
                    return 2;
                }
                ocb++;
                check = true;
                operandSymbolNumber = 0;
            } else {
                check = false;
            }
            if(in.charAt(i) == ')' && !check){
                ocb--;
                if(ocb < 0){
                    return 0;
                }
                check = true;
                operandSymbolNumber = 0;
            }
            int symbolNumber = in.charAt(i);
            if((symbolNumber < 91 && symbolNumber > 64) || (symbolNumber == 49 || symbolNumber == 48) && !check){ // проверка на буквы и 1 0
                check = true;
                if (operandStreak == 0){
                    symbolStreak++;
                    symbolQuantity++;
                    operandSymbolNumber = symbolNumber;
                } else {
                    operandStreak--;
                    symbolStreak--;
                    symbolQuantity++;
                    if (symbolNumber == operandSymbolNumber){
                        return 0;
                    }
                }
            }
            if (symbolNumber == 45 && !check) { // проверка на ->
                i++;
                symbolNumber = in.charAt(i);
                if (symbolNumber == 62){
                    check = true;
                    operandStreak++;
                } else {
                    return 0;
                }
            }
            if (symbolNumber == 126 && !check){ // проверка на ~
                check = true;
                operandStreak++;
            }
            if (symbolNumber == 92 && !check) { // проверка на \/
                i++;
                symbolNumber = in.charAt(i);
                if (symbolNumber == 47){
                    check = true;
                    operandStreak++;
                } else {
                    return 0;
                }
            }
            if (symbolNumber == 47 && !check) { // проверка на /\
                i++;
                symbolNumber = in.charAt(i);
                if (symbolNumber == 92){
                    check = true;
                    operandStreak++;
                } else {
                    return 0;
                }
            }
            if (symbolNumber == 33 && !check){ // проверка на !
                operandStreak++;
                symbolStreak++;
                check = true;
            }
            if(!check){
                return 0;
            }
            if (symbolStreak > 1 || operandStreak > 1){
                return  0;
            }
        }
        int lastSymbol = in.charAt(in.length() - 1);
        if(!(lastSymbol < 91 && lastSymbol > 64) && lastSymbol != 41){
            return 0;
        }
        if (symbolQuantity < 2){
            return 0;
        }
        if(ocb == 0) {
            return 1;
        }
        return 0;
    }

    public static String deleteSpaces(String in){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) != ' '){
                res.append(in.charAt(i));
            }
        }
        return res.toString();
    }

    public static String removeNeg(String in){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) != '!'){
                res.append(in.charAt(i));
            }
        }
        return res.toString();
    }

    public static boolean noDoubleNeg(String in){
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '!'){
                if(in.charAt(i + 1) == '!'){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean noImpNEq(String in){
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '>' || in.charAt(i) == '~'){
                return false;
            }
        }
        return true;
    }

    public static boolean isDNF(String in){
        if (!noDoubleNeg(in) && !noImpNEq(in)){
            return false;
        }
        int depth = 0;
        String formula = removeNeg(in);
        for(int i = 0; i < formula.length(); i++){
            char ch = formula.charAt(i);
            switch (ch) {
                case '(' -> {
                    depth++;
                    if (depth == 3) {
                        return false;
                    }
                }
                case ')' -> depth--;
                case '/' -> {
                    if (depth == 1) {
                        return false;
                    }
                    i++;
                }
                case '\\' -> {
                    if (depth == 2) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        if(input.equals("")){
            System.out.println("Необходимо ввести формулу!");
        }
        input = deleteSpaces(input);
        switch (checkSyntax(input)) {
            case 0 -> {
                System.out.println("Некорректное выражение!");
                return;
            }
            case 2 -> {
                System.out.println("Формула не является ДНФ");
                return;
            }
        }
        if (isDNF(input)) {
            System.out.println("Формула является ДНФ");
        } else {
            System.out.println("Формула не является ДНФ");

        }
    }
}
