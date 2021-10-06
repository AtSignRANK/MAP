package io.github.asr.mafp.loop

import io.github.asr.mafp.utils.Task
import io.github.asr.mafp.utils.wait
import org.bukkit.plugin.Plugin

lateinit var loopAction: Loop.() -> Unit

fun Plugin.loop(tickGab: Long, endLoop: Int, action: Loop.() -> Unit) {
    val loop = Loop(1)
    loopAction = action
    val task = server.scheduler.scheduleSyncRepeatingTask(
        this, {
            loopAction.invoke(loop)
            loop.looping()
        }, 0L, tickGab)

    wait(endLoop * tickGab) { server.scheduler.cancelTask(task) }
}

fun Plugin.loop(tickGab: Long, endLoop: Int, timeStart: Int, action: Loop.() -> Unit) {
    val loop = Loop(timeStart)
    loopAction = action
    val task = server.scheduler.scheduleSyncRepeatingTask(
        this, {
            loopAction.invoke(loop)
            loop.looping()
        }, 0L, tickGab)

    wait(endLoop * tickGab) { server.scheduler.cancelTask(task) }
}

class Loop(private var time: Int = 1) {

    fun time(): Int = time

    fun looping() { time++ }

    fun stopLoop() {
        loopAction = {}
    }
}