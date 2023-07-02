package dev.louis.chainendcrystals.mixin;

import dev.louis.chainendcrystals.entity.BoostCrystalEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ServerPlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyArgs(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    public void lessEndCrystalDamage(Args args) {
        DamageSource source = args.get(0);
        float amount = args.get(1);

        if(source.getSource() instanceof BoostCrystalEntity)amount = 0;

        args.set(0, source);
        args.set(1, amount);
    }
}
