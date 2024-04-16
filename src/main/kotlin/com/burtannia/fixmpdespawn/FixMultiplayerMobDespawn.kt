package com.burtannia.fixmpdespawn

import com.burtannia.fixmpdespawn.event.EntityMoveWorldEvent
import com.burtannia.fixmpdespawn.event.EntityMoveWorldHandler
import com.burtannia.fixmpdespawn.mixin.MobEntityPersistentDurationMixin
import net.fabricmc.api.ModInitializer
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import org.slf4j.LoggerFactory

object FixMultiplayerMobDespawn : ModInitializer {
    private const val MOD_ID: String = "fix-multiplayer-mob-despawn"
    private val logger = LoggerFactory.getLogger(MOD_ID)
    private const val PERSIST_DURATION: Long = 60 * 1000 // 60 seconds

    override fun onInitialize() {
        logger.info("Initialising $MOD_ID...")
        registerHandlers()
        logger.info("Finished initialising $MOD_ID")
    }

    private fun registerHandlers() {
        EntityMoveWorldHandler.EVENT.register(keepAliveOnMoveWorld)
    }

    private val keepAliveOnMoveWorld = object : EntityMoveWorldHandler {
        override fun handle(event: EntityMoveWorldEvent): ActionResult {
            val entity = event.entity

            if (entity.world is ServerWorld && entity is MobEntityPersistentDurationMixin)
                entity.setPersistentFor(PERSIST_DURATION)

            return ActionResult.PASS
        }
    }
}