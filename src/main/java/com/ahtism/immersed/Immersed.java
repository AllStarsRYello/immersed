package com.ahtism.immersed;

import com.ahtism.immersed.config.ImmersedConfig;
import net.fabricmc.api.ModInitializer;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Immersed implements ModInitializer {
    public static final ImmersedConfig CONFIG = ImmersedConfig.createAndLoad();
    public static final Logger LOGGER = LoggerFactory.getLogger("immersed");

    public static OkHttpClient okHttpClient = new OkHttpClient();

    public static final int TIME_UPDATE_INTERVAL = 0; // Update time every tick
    public static final int WEATHER_UPDATE_INTERVAL = 20 * 15; // Update weather every 15 seconds

    @Override
    public void onInitialize() {}
}
