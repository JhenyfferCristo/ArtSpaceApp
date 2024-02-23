package com.example.artspaceapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtImage(
   @StringRes val titleResourceId: Int,
   @StringRes val authorResourceId: Int,
   @DrawableRes val imageResourceId: Int

)
