package com.bimzygames.apegame.debug;

public class Logger {

    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_GREEN = "\u001B[32m";
    public static void Log(String... strings)
    {
        StringBuilder result = new StringBuilder();
        result.append(ANSI_GREEN);
        result.append("Game Log - ");
        for (String str : strings) {
            result.append(str);
        }
        result.append(ANSI_RESET);
        System.out.println(result.toString());
    }
}
