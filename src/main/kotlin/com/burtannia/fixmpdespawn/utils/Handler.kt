package com.burtannia.fixmpdespawn.utils

import net.minecraft.util.ActionResult

interface Handler<E> {
    fun handle(event: E): ActionResult
}

fun <E, H : Handler<E>>flattenHandlers(handlers: Array<H>): Handler<E> {
    return object : Handler<E> {
        override fun handle(event: E): ActionResult {
            handlers.fold(ActionResult.PASS) { acc, handler ->
                if (acc != ActionResult.PASS) acc else handler.handle(event)
            }

            return ActionResult.PASS
        }
    }
}