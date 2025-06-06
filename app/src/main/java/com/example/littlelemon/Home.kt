package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun Home(navHostController: NavHostController) {
    Box(modifier = Modifier.statusBarsPadding()) {
        HomeHeader(navHostController)
    }
}


@Composable
fun HomeHeader(navHostController: NavHostController) {
    Row(
        modifier = Modifier.height(55.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.height(45.dp)
        )
        Spacer(modifier = Modifier.weight(0.15f))
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(45.dp)
                .clickable { navHostController.navigate(ProfileDes.route) }
        )
        Spacer(
            modifier = Modifier
                .weight(0.02f)
        )
    }
}