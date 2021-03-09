package dev.ebnbin.ebdev

import dev.ebnbin.eb.Pref
import dev.ebnbin.eb.appId

object EBDevPrefs {
    val name: String = "$appId.ebdev"

    val dev_page: Pref<Int> = Pref.create("dev_page", 0, name)
    val dev_floating_x: Pref<Int> = Pref.create("dev_floating_x", 0, name)
    val dev_floating_y: Pref<Int> = Pref.create("dev_floating_y", 0, name)
}
