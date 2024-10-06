package com.bbeniful.designplans.designList.utilities

import com.bbeniful.designplans.designList.models.Design
import com.bbeniful.designplans.navigation.AnimateLayoutChange
import com.bbeniful.designplans.navigation.AnimatedColorBGNav
import com.bbeniful.designplans.navigation.Buttons
import com.bbeniful.designplans.navigation.Calendar
import com.bbeniful.designplans.navigation.ClickTextNav
import com.bbeniful.designplans.navigation.CloudSunAnimation
import com.bbeniful.designplans.navigation.ColorChange
import com.bbeniful.designplans.navigation.MekisUINav
import com.bbeniful.designplans.navigation.GamingUINav
import com.bbeniful.designplans.navigation.NavigationDrawer
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

    class GamingUI : Designs(
        design = Design.GamingUI,
        path = GamingUINav
    )

    class Calendar : Designs(
        design = Design.Calendar,
        path = Calendar
    )

    class ClickText : Designs(
        design = Design.ClickText,
        path = ClickTextNav
    )
    class AnimatedColorBG : Designs(
        design = Design.AnimatedColorBG,
        path = AnimatedColorBGNav
    )

    class MekisUI : Designs(
        design = Design.MekisUINav,
        path = MekisUINav
    )

    class NavigationDrawer: Designs(
        design = Design.NavigationDrawer,
        path = NavigationDrawer
    )
}

val designs: List<Designs>
    get() = arrayListOf(
        Designs.ColorChange(),
        Designs.Buttons(),
        Designs.SharedElement(),
        Designs.AnimateLayoutChange(),
        Designs.CloudSunAnimation(),
        Designs.GamingUI(),
        Designs.Calendar(),
        Designs.ClickText(),
        Designs.AnimatedColorBG(),
        Designs.MekisUI(),
        Designs.NavigationDrawer()

    )