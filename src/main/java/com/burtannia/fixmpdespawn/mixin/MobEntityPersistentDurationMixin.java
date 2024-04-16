package com.burtannia.fixmpdespawn.mixin;

import com.burtannia.fixmpdespawn.utils.PersistentFor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Unique
    private static final Logger fix_multiplayer_mob_despawn$logger = LoggerFactory.getLogger("fix-multiplayer-mob-despawn");

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        if (fix_mp_mob_despawn$persistentTicks == null) {
            return;
        }

        if (fix_mp_mob_despawn$persistentTicks == 0) {
            fix_multiplayer_mob_despawn$logger.debug("removing persistent for {}", this.getId());
            this.persistent = false;
            this.fix_mp_mob_despawn$persistentTicks = null;
            return;
        }

        fix_mp_mob_despawn$persistentTicks--;
    }

    @Override
    public void fix_mp_mob_despawn$setPersistentFor(long ticks) {
        fix_multiplayer_mob_despawn$logger.debug("persisting {} for {} ticks", this.getId(), ticks);
        if (ticks <= 0) {
            this.persistent = false;
            this.fix_mp_mob_despawn$persistentTicks = null;
        } else {
            this.persistent = true;
            this.fix_mp_mob_despawn$persistentTicks = ticks;
        }
    }
}
