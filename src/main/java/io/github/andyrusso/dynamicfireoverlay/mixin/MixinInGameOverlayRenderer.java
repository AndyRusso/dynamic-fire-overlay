package io.github.andyrusso.dynamicfireoverlay.mixin;

import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameOverlayRenderer.class)
public class MixinInGameOverlayRenderer {
	@Redirect(method = "renderOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isOnFire()Z"))
	private static boolean isOnFireAndNoFireResistance(ClientPlayerEntity instance) {
		return instance.isOnFire() && !instance.hasStatusEffect(StatusEffects.FIRE_RESISTANCE);
	}
}