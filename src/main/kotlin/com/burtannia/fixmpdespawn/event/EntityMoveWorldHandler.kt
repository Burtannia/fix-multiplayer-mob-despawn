package com.burtannia.fixmpdespawn.event

import com.burtannia.fixmpdespawn.utils.Handler
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.entity.Entity
import net.minecraft.util.ActionResult

data class EntityMoveWorldEvent(
    val entity: Entity,
)

interface EntityMoveWorldHandler : Handler<EntityMoveWorldEvent> {
    companion object {
        val EVENT: Event<EntityMoveWorldHandler> = EventFactory.createArrayBacked(
            EntityMoveWorldHandler::class.java
        ) { handlers -> flattenHandlers(handlers) }
    }
}

private fun flattenHandlers(handlers: Array<EntityMoveWorldHandler>): EntityMoveWorldHandler {
    return object : EntityMoveWorldHandler {
        override fun handle(event: EntityMoveWorldEvent): ActionResult {
            handlers.fold(ActionResult.PASS) { acc, handler ->
                if (acc != ActionResult.PASS) acc else handler.handle(event)
            }

            return ActionResult.PASS
        }
    }
}