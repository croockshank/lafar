package com.genadidharma.lafar.ui.restaurant

import androidx.lifecycle.LiveData
import com.genadidharma.lafar.data.Menu
import com.genadidharma.lafar.data.MenuType

data class MenuSectionViewState(
        val type: MenuType? = null,
        val menus: LiveData<List<Menu>>? = null
)