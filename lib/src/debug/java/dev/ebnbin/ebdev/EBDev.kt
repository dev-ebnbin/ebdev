package dev.ebnbin.ebdev

import android.app.Activity

object EBDev {
    data class Config(
        val createDevPageList: (Activity) -> List<DevPage> = { emptyList() },
        val devFloatingExceptActivityNameSet: Set<String> = emptySet(),
    ) {
        internal fun createDevPageList(): (Activity) -> List<DevPage> {
            return {
                listOf(
                    DevPage(
                        title = "EB",
                        fragmentClassName = DevEBPageFragment::class.java.name,
                        fragmentArgs = DevEBPageFragment.createArgs(it),
                    ),
                ) + createDevPageList(it)
            }
        }

        internal fun devFloatingExceptActivityNameSet(): Set<String> {
            return setOf(
                CrashActivity::class.java.name,
            ) + devFloatingExceptActivityNameSet
        }
    }

    var config: Config = Config()
}
