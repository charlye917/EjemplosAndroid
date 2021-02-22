package com.charlye934.burgermenu.toolbar

import androidx.appcompat.widget.Toolbar


interface IToolbar {
    fun toolbarToLoad(toolbar: Toolbar?)
    fun enableHomeDisplay(value: Boolean)
}