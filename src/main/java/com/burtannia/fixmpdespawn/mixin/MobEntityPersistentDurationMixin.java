package com.burtannia.fixmpdespawn.mixin;

import com.burtannia.fixmpdespawn.utils.PersistentFor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityPersistentDurationMixin extends LivingEntity implements PersistentFor {
    @Shadow
    private boolean persistent;

    @Unique
    @Nullable
    private Long fix_mp_mob_despawn$persistentTicks = null;

    protected MobEntityPersistentDurationMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        if (fix_mp_mob_despawn$persistentTicks == null)
            return;

        if (fix_mp_mob_despawn$persistentTicks == 0) {
            this.persistent = false;
            this.fix_mp_mob_despawn$persistentTicks = null;
            return;
        }

        fix_mp_mob_despawn$persistentTicks--;
    }

    @Override
    public void fix_mp_mob_despawn$setPersistentFor(long ticks) {
        if (ticks <= 0) {
            this.persistent = false;
            this.fix_mp_mob_despawn$persistentTicks = null;
        } else {
            this.persistent = true;
            this.fix_mp_mob_despawn$persistentTicks = ticks;
        }
    }
}
