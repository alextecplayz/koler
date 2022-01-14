package com.chooloo.www.koler.ui.settings

import android.view.MenuItem
import com.chooloo.www.chooloolib.interactor.preferences.PreferencesInteractor.Companion.AccentTheme.*
import com.chooloo.www.chooloolib.ui.base.BaseController
import com.chooloo.www.koler.R
import com.chooloo.www.koler.ui.main.MainActivity

class SettingsController<V : SettingsContract.View>(view: V) :
    BaseController<V>(view),
    SettingsContract.Controller<V> {

    override fun onMenuItemClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.menu_main_rate -> component.navigations.rateApp()
            R.id.menu_main_email -> component.navigations.sendEmail()
            R.id.menu_main_donate -> component.navigations.donate()
            R.id.menu_main_ask_sim -> component.dialogs.askForShouldAskSim {
                component.preferences.isAskSim = it
            }
            R.id.menu_main_animations -> component.dialogs.askForAnimations {
                component.preferences.isAnimations = it
            }
            R.id.menu_main_report_bugs -> component.navigations.reportBug()
            R.id.menu_main_accent_color -> component.dialogs.askForColor(R.array.accent_colors, {
                component.preferences.accentTheme = when (it) {
                    component.colors.getColor(R.color.red_background) -> RED
                    component.colors.getColor(R.color.blue_background) -> BLUE
                    component.colors.getColor(R.color.green_background) -> GREEN
                    component.colors.getColor(R.color.orange_background) -> ORANGE
                    component.colors.getColor(R.color.purple_background) -> PURPLE
                    else -> DEFAULT
                }
                component.navigations.goToActivity(MainActivity::class.java)
            })
            R.id.menu_main_compact_mode -> component.dialogs.askForCompact {
                component.preferences.isCompact = it
            }
            R.id.menu_main_default_page -> component.dialogs.askForDefaultPage {
                component.preferences.defaultPage = it
            }
        }
    }
}