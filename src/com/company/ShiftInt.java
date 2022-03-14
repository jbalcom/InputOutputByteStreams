package com.company;

public class ShiftInt {
    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_BLACK = "\u001b[30m";
    private static final String ANSI_RED = "\u001b[31m";
    private static final String ANSI_GREEN = "\u001b[32m";
    private static final String ANSI_YELLOW = "\u001b[33m";
    private static final String ANSI_BLUE = "\u001b[34m";
    private static final String ANSI_PURPLE = "\u001b[35m";
    private static final String ANSI_CYAN = "\u001b[36m";
    private static final String ANSI_WHITE = "\u001b[37m";

    public static void main(String[] args) {
        int x = 922342959;

        writeInt(x);
    }

    private static void display(int x){
        String all = String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0");
        String colouredBinary = all.substring(0, 24) + ANSI_PURPLE + all.substring(24) + ANSI_RESET;
        int y = x & 0xFF;
        String output = String.format("%10d and 0xFF is %8s \t %10d is ", y, Integer.toBinaryString(y), x) + colouredBinary;
        System.out.println(output);
    }

    private static void writeInt(int v){
        int x;
        display(v >>> 24);
        display(v >>> 16);
        display(v >>> 8);
        display(v >>> 0);
    }

}
