package com.burtannia.fixmpdespawn.utils

import net.minecraft.util.ActionResult

interface Handler<E> {
    fun handle(event: E): ActionResult
}
