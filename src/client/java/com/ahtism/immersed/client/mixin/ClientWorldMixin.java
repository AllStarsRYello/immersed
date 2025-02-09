package com.ahtism.immersed.client.mixin;

import com.ahtism.immersed.Immersed;
import com.ahtism.immersed.client.ImmersedClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.Properties.class)
public class ClientWorldMixin {
    @Inject(method = "getTimeOfDay", at = @At("HEAD"), cancellable = true)
    private void getTimeOfDay(CallbackInfoReturnable<Long> cir) {
        if (Immersed.CONFIG.realTimeOfDay())
            cir.setReturnValue((long) ImmersedClient.timeOfDay);
    }

    @Inject(method = "isRaining", at = @At("HEAD"), cancellable = true)
    private void isRaining(CallbackInfoReturnable<Boolean> cir) {
        if (Immersed.CONFIG.realWeather())
            cir.setReturnValue(ImmersedClient.raining);
    }

    @Inject(method = "isThundering", at = @At("HEAD"), cancellable = true)
    private void isThundering(CallbackInfoReturnable<Boolean> cir) {
        if (Immersed.CONFIG.realWeather())
            cir.setReturnValue(ImmersedClient.thundering);
    }
}