package dev.louis.chainendcrystals.mixin;

import dev.louis.chainendcrystals.entity.BoostCrystalEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "isInvulnerableTo", at = @At(value = "HEAD"),cancellable = true)
    public void noBoostCrystalDamage(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if(damageSource.getSource() instanceof BoostCrystalEntity)cir.setReturnValue(true);
    }
}
