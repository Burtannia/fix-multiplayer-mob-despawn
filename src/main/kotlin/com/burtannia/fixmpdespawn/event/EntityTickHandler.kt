package com.burtannia.fixmpdespawn.event

import com.burtannia.fixmpdespawn.utils.Handler
import com.burtannia.fixmpdespawn.utils.flattenHandlers
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.entity.Entity

data class EntityTickEvent(
    val entity: Entity
)

interface EntityTickHandler : Handler<EntityTickEvent> {
    companion object {
        val EVENT: Event<EntityTickHandler> = EventFactory.createArrayBacked(
            EntityTickHandler::class.java
        ) { handlers -> flattenHandlers(handlers) as EntityTickHandler }
    }
}