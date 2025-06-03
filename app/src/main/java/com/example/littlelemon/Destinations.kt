package com.example.littlelemon

interface Destinations {
    val route: String
}

object HomeDes : Destinations {
    override val route: String = "home"
}

object ProfileDes : Destinations {
    override val route: String = "profile"
}

object OnboardingDes : Destinations {
    override val route: String = "onboarding"
}