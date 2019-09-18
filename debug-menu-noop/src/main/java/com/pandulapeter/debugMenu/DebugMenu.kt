package com.pandulapeter.debugMenu

import android.app.Activity
import android.app.Application
import com.pandulapeter.debugMenuCore.DebugMenu
import com.pandulapeter.debugMenuCore.DebugMenuConfiguration

object DebugMenu : DebugMenu {

    override fun initialize(application: Application, configuration: DebugMenuConfiguration) = Unit

    override fun closeDrawerIfOpen(activity: Activity) = false
}