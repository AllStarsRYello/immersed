package com.ahtism.immersed.config;

import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "immersed")
@Config(name = "immersed", wrapperName = "ImmersedConfig")
public class ImmersedConfigModel {
    @SectionHeader("featureToggles")
    public boolean realTimeOfDay = true;
    public boolean realWeather = true;

    @SectionHeader("weatherSettings")
    public boolean realVisibility = true;

    @RangeConstraint(min = 2, max = 32)
    public int targetVisibility = 12;

    @RangeConstraint(min = 2, max = 32)
    public int minVisibility = 4;

    @RangeConstraint(min = 2, max = 32)
    public int maxVisibility = 24;

    @SectionHeader("locationSettings")
    @RangeConstraint(min = -180, max = 180)
    public double latitude = 0;

    @RangeConstraint(min = -180, max = 180)
    public double longitude = 0;
}
