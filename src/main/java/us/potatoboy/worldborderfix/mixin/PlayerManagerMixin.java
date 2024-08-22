package us.potatoboy.worldborderfix.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @ModifyArg(
        method = "sendWorldInfo",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/network/packet/s2c/play/WorldBorderInitializeS2CPacket;<init>(Lnet/minecraft/world/border/WorldBorder;)V"
        ),
        index = 0
    )
    private WorldBorder getPlayerWorldBorder(WorldBorder worldBorder, @Local(argsOnly = true) ServerWorld world) {
        return world.getWorldBorder();
    }
}
