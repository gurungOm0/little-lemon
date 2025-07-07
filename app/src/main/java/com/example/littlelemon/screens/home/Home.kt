package com.example.littlelemon.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.Green1
import com.example.littlelemon.ui.theme.KarlaTextFontFamily
import com.example.littlelemon.ui.theme.MarkaziTextFontFamily
import com.example.littlelemon.ui.theme.Yellow1
import com.bumptech.glide.integration.compose.placeholder
import com.example.littlelemon.navigation.ProfileDes
import com.example.littlelemon.R
import com.example.littlelemon.data.local.MenuItem
import com.example.littlelemon.ui.theme.LightGrey


@Composable
fun Home(navHostController: NavHostController, menuList: List<MenuItem>) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxHeight()
    ) {
        HomeHeader(navHostController)
        HeroAndRest(menuList)
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


@Composable
fun HeroAndRest(menuList: List<MenuItem>) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Green1)
                .padding(vertical = 25.dp, horizontal = 25.dp)
        ) {
            Column {
                Text(
                    text = "Little Lemon",
                    color = Yellow1,
                    fontSize = 55.sp,
                    fontFamily = MarkaziTextFontFamily
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                ) {
                    Column {
                        Text(
                            "Chicago",
                            color = Color.White,
                            fontSize = 40.sp,
                            fontFamily = MarkaziTextFontFamily
                        )
                        Text(
                            "We are a family owned \nMediterranean restaurant,\nfocused on traditional \nrecipes served with a\nmodern twist.",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = KarlaTextFontFamily
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 135.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                    )
                }
            }
        }
        Searcher(menuList)
    }

}


//decorationBox = { innerTextField ->
//    if (searchPhrase.toString() == "") {
//        Text(text = "Enter search phrase", color = Color.Gray)
//    }
//    innerTextField()
//}

@Composable
fun Searcher(menuList: List<MenuItem>) {
    var searchChar by remember { mutableStateOf("") }
    fun searchFilterData(): List<MenuItem> {
        if (searchChar == "") return menuList
        return menuList.filter { it.title.contains(searchChar, ignoreCase = true) }
    }
    Column {
        Row(
            modifier = Modifier
                .height(55.dp)
                .background(color = Color.White, RoundedCornerShape(10))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                Modifier.padding(horizontal = 15.dp)
            )
            TextField(
                value = searchChar,
                onValueChange = { searchChar = it },
                maxLines = 1,
                placeholder = { Text("Enter search phrase!") },
                textStyle = TextStyle(fontFamily = KarlaTextFontFamily, fontSize = 20.sp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box {
            CategorySelector(searchFilterData())
        }
    }
}


@Composable
fun CategorySelector(menuList: List<MenuItem>) {
    var clickedCat by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    fun filterCat(): List<MenuItem> {
        if (clickedCat == "") {
            return menuList
        }
        return menuList.filter { it.category == clickedCat }
    }
    Column {
        Text(
            "ORDER FOR DELIVERY!", fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
        )
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .width(450.dp)
                .clip(RoundedCornerShape(5)), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clickable(enabled = true, "All", role = Role.Button)
                    { clickedCat = "" }
                    .height(45.dp)
                    .width(intrinsicSize = IntrinsicSize.Min)
                    .background(LightGrey, RoundedCornerShape(40))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "All",
                    color = Green1, fontSize = 23.sp
                )
            }
            Box(
                modifier = Modifier
                    .clickable(enabled = true, "Starters", role = Role.Button)
                    { clickedCat = "starters" }
                    .height(45.dp)
                    .clip(RoundedCornerShape(40))
                    .width(intrinsicSize = IntrinsicSize.Min)
                    .background(LightGrey, RoundedCornerShape(40))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Starters",
                    color = Green1, fontSize = 23.sp
                )
            }
            Box(
                modifier = Modifier
                    .clickable(enabled = true, "Mains", role = Role.Button)
                    { clickedCat = "mains" }
                    .height(45.dp)
                    .clip(RoundedCornerShape(40))
                    .width(intrinsicSize = IntrinsicSize.Min)
                    .background(LightGrey, RoundedCornerShape(40))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Mains",
                    color = Green1,
                    fontSize = 23.sp
                )
            }
            Box(
                modifier = Modifier
                    .clickable(enabled = true, "Desserts", role = Role.Button)
                    { clickedCat = "desserts" }
                    .height(45.dp)
                    .clip(RoundedCornerShape(40))
                    .width(intrinsicSize = IntrinsicSize.Min)
                    .background(LightGrey, RoundedCornerShape(40))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Desserts",
                    color = Green1, fontSize = 23.sp,
                    minLines = 1
                )
            }
        }
        MenuItemsCol(filterCat())
    }
}

@Composable
fun MenuItemsCol(menuList: List<MenuItem>) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .background(Green1)
        )
        LazyColumn {
            items(
                items = menuList,
                itemContent = {
                    MenuItems(it)
                    Log.i("DB", it.toString())
                }
            )

        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuItems: MenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                menuItems.title,
                fontFamily = KarlaTextFontFamily,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                menuItems.description,
                fontFamily = KarlaTextFontFamily,
                fontSize = 12.sp,
                color = Green1
            )
            Text(
                menuItems.price.toString(),
                fontFamily = KarlaTextFontFamily,
                fontSize = 16.sp,
                color = Green1
            )
        }
        Box(
            modifier = Modifier
                .size(width = 90.dp, height = 90.dp)
                .border(width = 0.5.dp, color = Green1)
        ) {
            GlideImage(
                model = menuItems.image,
                contentDescription = menuItems.title,
                contentScale = ContentScale.Fit,
                loading = placeholder(R.drawable.ic_launcher_background),
                failure = placeholder(R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxSize()
            )
        }
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(Green1)
    )
}
