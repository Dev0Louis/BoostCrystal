package dev.louis.chainendcrystals.entity;

import dev.louis.chainendcrystals.BoostCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;

public class BoostCrystalEntity extends Entity {
    public int endCrystalAge;

    public BoostCrystalEntity(EntityType<? extends BoostCrystalEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
        this.endCrystalAge = this.random.nextInt(100000);
    }

    public BoostCrystalEntity(World world, double x, double y, double z) {
        this(BoostCrystal.BOOST_CRYSTAL_ENTITY, world);
        this.setPosition(x, y, z);
    }

    @Override
    public void tick() {
        endCrystalAge++;
    }

    @Override
    protected void initDataTracker() {

    }
    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source))return false;
        if (!this.isRemoved() && !this.getWorld().isClient) {
            this.remove(Entity.RemovalReason.KILLED);
            if (!source.isIn(DamageTypeTags.IS_EXPLOSION)) {
                DamageSource damageSource = source.getAttacker() != null ? this.getDamageSources().explosion(this, source.getAttacker()) : null;
                this.getWorld().createExplosion(this, damageSource, null, this.getX(), this.getY(), this.getZ(), 6.0f, false, World.ExplosionSourceType.NONE);
            }
        }
        return true;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
