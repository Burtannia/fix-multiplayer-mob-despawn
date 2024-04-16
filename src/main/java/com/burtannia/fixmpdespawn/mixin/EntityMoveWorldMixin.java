package com.burtannia.fixmpdespawn.mixin;

import com.burtannia.fixmpdespawn.event.EntityMoveWorldEvent;
import com.burtannia.fixmpdespawn.event.EntityMoveWorldHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public class EntityMoveWorldMixin {
    @Inject(at = @At("TAIL"), method = "moveToWorld")
    private void onMove(ServerWorld destination, CallbackInfoReturnable<Entity> callback) {
        EntityMoveWorldEvent event = new EntityMoveWorldEvent(callback.getReturnValue());
        ActionResult _result = EntityMoveWorldHandler.Companion.getEVENT().invoker().handle(event);
    }
}