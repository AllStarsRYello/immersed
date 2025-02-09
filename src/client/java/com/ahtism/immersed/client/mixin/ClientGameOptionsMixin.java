package com.ahtism.immersed.client.mixin;

import com.ahtism.immersed.Immersed;
import com.ahtism.immersed.client.ImmersedClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public class ClientGameOptionsMixin {
    @Inject(method = "getClampedViewDistance", at = @At("HEAD"), cancellable = true)
    private void getClampedViewDistance(CallbackInfoReturnable<Integer> cir) {
        if (Immersed.CONFIG.realVisibility()) {
            cir.setReturnValue(ImmersedClient.visibility);
        }
    }
}
