package com.ahtism.immersed.client;

import com.ahtism.immersed.Immersed;
import com.ahtism.immersed.weather.WeatherInfo;
import com.ahtism.immersed.weather.WeatherManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

import java.time.LocalTime;

public class ImmersedClient implements ClientModInitializer {
    public static int timeOfDay;
    public static boolean raining;
    public static boolean thundering;

    public static boolean visibilityChanged = true;
    public static int visibility;

    @Override
    public void onInitializeClient() {
        visibility = Immersed.CONFIG.targetVisibility();
        ClientTickEvents.END_CLIENT_TICK.register(ImmersedClient::tick);
    }

    private static int timeTicksPassed = 0;
    private static int weatherTicksPassed = 0;
    private static void tick(MinecraftClient client) {
        timeTicksPassed++;
        weatherTicksPassed++;

        if (timeTicksPassed >= Immersed.TIME_UPDATE_INTERVAL) {
            timeTicksPassed = 0;
            updateTime(client);
        }

        if (weatherTicksPassed >= Immersed.WEATHER_UPDATE_INTERVAL) {
            weatherTicksPassed = 0;
            updateWeather(client);
        }
    }

    private static void updateTime(MinecraftClient client) {
        ClientWorld world = client.world;

        if (world != null && Immersed.CONFIG.realTimeOfDay()) {
            int timeTicks = (int) (LocalTime.now().toSecondOfDay() / 3.6);
            timeOfDay = (timeTicks - 6000 + 24000) % 24000;
        }
    }

    private static void updateWeather(MinecraftClient client) {
        ClientWorld world = client.world;

        if (world != null && Immersed.CONFIG.realWeather()) {
            WeatherInfo info = WeatherManager.getWeatherInfo();

            if (Immersed.CONFIG.realVisibility()) {
                float normalizedVisibility = (float) info.getVisibility() / 25000;

                int oldVisibility = visibility;
                visibility = (int) Math.max(Immersed.CONFIG.minVisibility(), Math.min(Immersed.CONFIG.targetVisibility() * normalizedVisibility, Immersed.CONFIG.maxVisibility()));
                visibilityChanged = visibility != oldVisibility;
            }

            raining = info.isRaining();
            thundering = info.isThundering();
        }
    }
}
