package com.karpin;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        WeatherApplication weatherApp = new WeatherApplication();

        System.out.println("Приложение для получения информации о погоде.");
        System.out.println("Доступные города: Москва (MOW), Санкт-Петербург (LED), Казань (KZN)");
        while (true) {
            System.out.println("Введите код города (или 'exit' для выхода).");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            weatherApp.displayWeather(command);
        }
    }
}