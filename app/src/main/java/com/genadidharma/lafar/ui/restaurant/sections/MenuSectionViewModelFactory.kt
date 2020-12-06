package com.genadidharma.lafar.ui.restaurant.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MenuSectionViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MenuSectionViewModel::class.java)){
            return MenuSectionViewModel() as T
        }
        throw IllegalArgumentException()
    }
}