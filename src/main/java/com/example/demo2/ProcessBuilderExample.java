package com.example.demo2;

import java.io.IOException;

/**
 * Исполнение команд с помощью
 * ProcessBuilder
 * @author G.I.V.
 * @since {@code }
 */
public class ProcessBuilderExample {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

//        // Создать объект ProcessBuilder с указанием имени сценария bash-скрипта и аргументов
//        ProcessBuilder builder = new ProcessBuilder("my-script.sh", "arg1", "arg2");
//
//        // Запустить сценарий
//        builder.start();
//        // Запустить сценарий и получить код возврата
//        int exitCode = builder.start().exitValue();
//
//        // Вывести код возврата в консоль
//        System.out.println(exitCode);


        // Создать объект ProcessBuilder с указанием имени сценария bash-скрипта

        //ProcessBuilder builderShell = new ProcessBuilder("my-script.sh");

        ProcessBuilder builderBat = new ProcessBuilder("my-script.bat");


        ProcessBuilder processBuilder = new ProcessBuilder("java", "-version");
        Process process = processBuilder.start();




//
//        builderBat.command("");
//        builderBat.directory();
//        builderBat.environment();
//
//        // Запустить сценарий .sh
//        Process start = builderBat.start();
//        try {
//            start.waitFor();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // Запустить сценарий .sh
        // builderShell.start();

    }
}
