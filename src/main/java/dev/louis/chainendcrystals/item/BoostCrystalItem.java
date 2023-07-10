package dev.louis.chainendcrystals.item;

import dev.louis.chainendcrystals.BoostCrystal;
import dev.louis.chainendcrystals.entity.BoostCrystalEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class BoostCrystalItem extends Item {
    public BoostCrystalItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();

        var pos = context.getBlockPos().up();
        var player = context.getPlayer();
        if(player == null)return ActionResult.FAIL;
        if (!world.isAir(pos))return ActionResult.FAIL;
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        List<Entity> list = world.getOtherEntities(null, new Box(x, y, z, x + 1.0, y + 2.0, z + 1.0));
        if (list.size() > 0)return ActionResult.FAIL;
        if (world instanceof ServerWorld) {
            BoostCrystalEntity crystal = new BoostCrystalEntity(world, x + 0.5, y, z + 0.5);
            world.spawnEntity(crystal);
            world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, pos);
        }
        context.getPlayer().getItemCooldownManager().set(context.getStack().getItem(), BoostCrystal.config.cooldown);
        return ActionResult.success(world.isClient);
    }


    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
