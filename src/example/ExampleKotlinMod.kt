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
            Log.info("World processors:")
            for (tile in Vars.world.tiles) {
                val build = tile.build
                if (build is LogicBlock.LogicBuild) {
                    Log.info("x: ${build.x()}, y: ${build.y()}, privileged: ${build.executor.privileged}, code: ${build.code}")
                    for (link in build.links) {
                        Log.info("- link name: ${link.name}, x: ${link.x}, y: ${link.y}")
                    }
                }
            }

            Log.info("Objectives:")
            for (objective in Vars.state.rules.objectives.all) {
                Log.info("- ${objective.text()}, flagsAdded: ${objective.flagsAdded.joinToString(";")}, flagsAdded: ${objective.flagsRemoved.joinToString(";")}, details: ${objective.details}")
            }
        }
    }

    override fun loadContent() {
        Log.info("Loading some example content.")
    }
}
