package com.burtannia.fixmpdespawn.mixin;

import com.burtannia.fixmpdespawn.event.EntityMoveWorldEvent;
import com.burtannia.fixmpdespawn.event.EntityMoveWorldHandler;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMoveWorldMixin {
    @Inject(at = @At("TAIL"), method = "moveToWorld")
    private void onMove(ServerWorld destination, CallbackInfoReturnable<Entity> callback) {
        EntityMoveWorldEvent event = new EntityMoveWorldEvent(callback.getReturnValue());
        EntityMoveWorldHandler.Companion.getEVENT().invoker().handle(event);
    }
}