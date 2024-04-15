package com.burtannia.fixmpdespawn.event

import com.burtannia.fixmpdespawn.utils.Handler
import com.burtannia.fixmpdespawn.utils.flattenHandlers
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.entity.Entity

data class EntityMoveWorldEvent(
    val entity: Entity,
)

interface EntityMoveWorldHandler : Handler<EntityMoveWorldEvent> {
    companion object {
        val EVENT: Event<EntityMoveWorldHandler> = EventFactory.createArrayBacked(
            EntityMoveWorldHandler::class.java
        ) { handlers -> flattenHandlers(handlers) as EntityMoveWorldHandler }
    }
}
