package com.bbeniful.designplans.designList.utilities

import com.bbeniful.designplans.designList.models.Design
import com.bbeniful.designplans.navigation.AnimateLayoutChange
import com.bbeniful.designplans.navigation.Buttons
import com.bbeniful.designplans.navigation.CloudSunAnimation
import com.bbeniful.designplans.navigation.ColorChange
import com.bbeniful.designplans.navigation.SharedList

sealed class Designs(val design: Design, val path: Any) {
    class ColorChange : Designs(
        design = Design.ColorChange,
        path = ColorChange
    )

    class Buttons : Designs(
        design = Design.Buttons,
        path = Buttons
    )

    class SharedElement : Designs(
        design = Design.SharedElementDesign,
        path = SharedList
    )

    class AnimateLayoutChange : Designs(
        design = Design.AnimateLayoutChange,
        path = AnimateLayoutChange
    )

    class CloudSunAnimation : Designs(
        design = Design.CloudSunAnimation,
        path = CloudSunAnimation
    )
}

val designs: List<Designs>
    get() = arrayListOf(
        Designs.ColorChange(),
        Designs.Buttons(),
        Designs.SharedElement(),
        Designs.AnimateLayoutChange(),
        Designs.CloudSunAnimation()
    )