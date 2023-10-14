package com.example.basicscompose.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrawableStringPair (
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
