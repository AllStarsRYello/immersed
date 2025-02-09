package com.ahtism.immersed.weather;

public class WeatherInfo {
    private int weatherCode;
    private int visibility;

    public WeatherInfo(int weatherCode, int visibility) {
        this.weatherCode = weatherCode;
        this.visibility = visibility;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public int getVisibility() {
        return visibility;
    }

    public boolean isRaining() {
        return weatherCode >= 50 && weatherCode < 95;
    }

    public boolean isThundering() {
        return weatherCode >= 95;
    }
}
