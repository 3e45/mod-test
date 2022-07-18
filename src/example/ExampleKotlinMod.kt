package example

import arc.*
import arc.func.Boolp
import arc.scene.ui.layout.Table
import arc.util.*
import mindustry.Vars
import mindustry.game.EventType.*
import mindustry.gen.Icon
import mindustry.mod.*
import mindustry.ui.Styles
import mindustry.ui.dialogs.*
import mindustry.world.blocks.logic.LogicBlock

class ExampleKotlinMod : Mod() {
    val table = Table()

    init {
        Events.run(ClientLoadEvent::class.java) {
            Log.info("ClientLoadEvent")
            table.fillParent = true
            table.top().left().defaults().size(45.0f)
            table.button(Icon.editor, Styles.cleari, 45.0f) {
                Log.info("clicked!!!")
            }.name("openExternalEditor")
            Vars.ui.hudGroup.addChild(table)
            table.visibility = Boolp { Vars.control.input.config.selected is LogicBlock.LogicBuild }
        }
        Events.run(WorldLoadEvent::class.java) {
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
                Log.info(
                    "- ${objective.text()}, flagsAdded: ${objective.flagsAdded.joinToString(";")}, flagsAdded: ${
                        objective.flagsRemoved.joinToString(
                            ";"
                        )
                    }, details: ${objective.details}"
                )
            }
        }

//        Events.run(Trigger.draw) {
//            val x = Vars.control.input.config.isShown && Vars.control.input.config.selected is LogicBlock.LogicBuild
//            table.visible = x
//        }
    }

    override fun loadContent() {
        Log.info("Loading some example content.")
    }
}
