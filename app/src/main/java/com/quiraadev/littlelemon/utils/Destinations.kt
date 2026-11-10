package com.quiraadev.littlelemon.utils

interface Destinations {
    var route: String
}

object Home: Destinations{
    override var route = "Home"
}

object Profile: Destinations{
    override var route = "Profile"
}

object Onboarding: Destinations{
    override var route = "Onboarding"
}

object DetailDish: Destinations{
    override var route = "DetailDish"
    const val argDish = "dish"
}