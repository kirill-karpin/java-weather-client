package com.karpin;

import java.util.HashMap;

public class WeatherApplication {
    private HashMap<String, String> weatherData;
    private WeatherClient client;

    public WeatherApplication() {
        initializedData();
    }

    // инициализация статических данных о погоде
    private void initializedData() {
        client = new WeatherClient();
    }

    // метод для отображения погоды
    public void displayWeather(String city) {
        // замените данные на динамические, полученные через weatherClient
        try {
            String weatherData = client.getWeatherData(city);
            System.out.println(weatherData);
        } catch (Exception e) {
            System.out.println("Во время выполнения запроса возникла ошибка. Проверьте, пожалуйста, параметры запроса и повторите попытку.");
        }

    }
}