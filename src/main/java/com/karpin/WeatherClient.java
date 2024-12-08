package com.karpin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherClient {
    private final HttpClient client;

    public WeatherClient() {
        client = HttpClient.newHttpClient();
    }

    public String getWeatherData(String city) throws IOException, InterruptedException {
        URI uri = URI.create("https://functions.yandexcloud.net/d4eo3a1nvqedpic89160?scale=C&city=" + city);
        String result = "Город: ";
        // создаём объект, описывающий HTTP-запрос
        HttpRequest request = HttpRequest.newBuilder() // получаем экземпляр билдера
                .GET()    // указываем HTTP-метод запроса
                .uri(uri) // указываем адрес ресурса
                .version(HttpClient.Version.HTTP_1_1) // указываем версию протокола
                .header("Accept", "text/html") // указываем заголовок Accept
                .build(); // заканчиваем настройку и создаём ("строим") http-запрос
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        if (response.statusCode() == 200) {

            JsonElement jsonElement = JsonParser.parseString(response.body());
            // преобразуем результат разбора текста в JSON-объект
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // получаем название страны
            JsonObject cityData = jsonObject.get("cities")
                    .getAsJsonObject().get(city).getAsJsonObject();
            String cityName = cityData.get("city").getAsString();
            String conditions = cityData.get("conditions").getAsString();
            String temperature = cityData.get("temperature").getAsString();

            return result + cityName + ". "
                    + conditions + ", "
                    + temperature;

        } else {
            return "Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode();
        }

    }
}
