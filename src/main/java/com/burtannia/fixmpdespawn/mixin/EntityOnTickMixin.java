package com.burtannia.fixmpdespawn.mixin;

import com.burtannia.fixmpdespawn.event.EntityTickEvent;
import com.burtannia.fixmpdespawn.event.EntityTickHandler;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityOnTickMixin {
    @SuppressWarnings("UnreachableCode")
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        EntityTickEvent event = new EntityTickEvent((Entity) (Object) this);
        EntityTickHandler.Companion.getEVENT().invoker().handle(event);
    }
}
