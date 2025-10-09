package com.junnew.features

import com.junnew.app.appcomponent.BaseViewModel
import com.junnew.core.data.preference.PreferenceHelper
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
): BaseViewModel() {

    fun isUserAuthenticated(): Boolean {
        return preferenceHelper.getLoggedIsStatus()
    }

}