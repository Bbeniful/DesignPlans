package com.bbeniful.designplans.designList.utilities

import com.bbeniful.designplans.designList.models.Design
import com.bbeniful.designplans.navigation.ColorChange

sealed class Designs(val design: Design, val path: Any) {
    class ColorChange : Designs(
        design = Design.ColorChange,
        path = ColorChange
    )
}


val designs: List<Designs>
    get() = arrayListOf(
        Designs.ColorChange()
    )