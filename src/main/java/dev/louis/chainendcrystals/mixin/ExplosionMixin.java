package dev.louis.chainendcrystals.mixin;

import dev.louis.chainendcrystals.entity.BoostCrystalEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow public abstract Entity getEntity();

    @ModifyVariable(
            method = "collectBlocksAndDamageEntities",
            at = @At("STORE"),
            ordinal = 1
    )
    private Vec3d modifyVec(Vec3d original) {
        System.out.println(original);
        if(this.getEntity() instanceof BoostCrystalEntity) original = original.multiply(3);
        return original;
    }
}
