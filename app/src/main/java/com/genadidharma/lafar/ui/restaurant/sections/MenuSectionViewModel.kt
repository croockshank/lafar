package com.genadidharma.lafar.ui.restaurant.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genadidharma.lafar.data.MenuType
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.data.RestaurantMenuItem
import com.genadidharma.lafar.ui.restaurant.MenuSectionViewState
import kotlinx.coroutines.launch

class MenuSectionViewModel : ViewModel() {
    private val mViewState = MutableLiveData<MenuSectionViewState>().apply {
        value = MenuSectionViewState()
    }

    val viewState: LiveData<MenuSectionViewState>
        get() = mViewState

    fun getMenus(restaurant: Restaurant?, type: MenuType?) = viewModelScope.launch {
        val data = RestaurantMenuItem.getMenus(restaurant, type)
        mViewState.value = mViewState.value?.copy(type = type, menus = data)
    }

}