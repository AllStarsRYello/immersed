package com.ahtism.immersed.weather;

import com.ahtism.immersed.Immersed;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class WeatherManager {
    public static WeatherInfo getWeatherInfo() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + Immersed.CONFIG.latitude() + "&longitude=" + Immersed.CONFIG.longitude() + "&current=weather_code&minutely_15=visibility&forecast_days=1";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response jsonResponse;
            jsonResponse = Immersed.okHttpClient.newCall(request).execute();

            assert jsonResponse.body() != null;
            String response = jsonResponse.body().string();
            JSONObject jsonObjectResponse = new JSONObject(response);

            String time = jsonObjectResponse.has("current") ? jsonObjectResponse.getJSONObject("current").getString("time") : null;
            int timeIndex = time != null ? getTimeIndex(time, jsonObjectResponse) : -1;

            int weatherCode = jsonObjectResponse.has("current") ? jsonObjectResponse.getJSONObject("current").getInt("weather_code") : -1;
            int visibility = jsonObjectResponse.has("minutely_15") && timeIndex != -1 ? jsonObjectResponse.getJSONObject("minutely_15").getJSONArray("visibility").getInt(timeIndex) : -1;
            if (weatherCode == -1 || visibility == -1) {
                Immersed.LOGGER.info("ERROR: Failed to get weather info!");
            }
            return new WeatherInfo(weatherCode, visibility);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getTimeIndex(String time, JSONObject jsonObject) {
        int index = -1;

        for (Object o : jsonObject.getJSONObject("minutely_15").getJSONArray("time")) {
            index++;
            String i = (String) o;
            if (Objects.equals(i, time)) {
                return index;
            }
        }

        return -1;
    }
}
