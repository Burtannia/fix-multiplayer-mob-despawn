package com.burtannia.fixmpdespawn.mixin;

import com.burtannia.fixmpdespawn.event.EntityMoveWorldEvent;
import com.burtannia.fixmpdespawn.event.EntityMoveWorldHandler;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMoveWorldMixin {
    @SuppressWarnings("UnreachableCode")
    @Inject(at = @At("HEAD"), method = "moveToWorld")
    private void onMove(ServerWorld destination, CallbackInfoReturnable<Entity> info) {
        EntityMoveWorldEvent event = new EntityMoveWorldEvent((Entity) (Object) this);
        ActionResult result = EntityMoveWorldHandler.Companion.getEVENT().invoker().handle(event);

        if (result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}