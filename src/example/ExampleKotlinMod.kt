package example

import arc.*
import arc.util.*
import mindustry.Vars
import mindustry.game.EventType.*
import mindustry.mod.*
import mindustry.ui.dialogs.*
import mindustry.world.blocks.logic.LogicBlock

class ExampleKotlinMod : Mod() {
    init {
        Events.on(WorldLoadEvent::class.java) {
            for (tile in Vars.world.tiles) {
                val build = tile.build
                if (build is LogicBlock.LogicBuild) {
                    Log.info("x: ${build.x()}, y: ${build.y()}, privileged: ${build.executor.privileged}, code: ${build.code}")
                }
            }
        }
    }

    override fun loadContent() {
        Log.info("Loading some example content.")
    }
}
