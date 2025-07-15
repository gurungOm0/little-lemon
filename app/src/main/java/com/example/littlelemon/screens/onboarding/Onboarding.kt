package com.example.littlelemon.screens.onboarding

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.navigation.HomeDes
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Green1
import com.example.littlelemon.ui.theme.KarlaTextFontFamily

@Composable
fun Onboarding(navHostController: NavHostController, viewModel: OnBoardingViewModel) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(androidx.compose.foundation.layout.WindowInsets.systemBars.asPaddingValues())
            .fillMaxHeight()
    ) {
        Header()
        Box(
            Modifier
                .background(color = Color(0xFF485e57))
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Let's get to know you",
                fontSize = 30.sp,
                fontFamily = KarlaTextFontFamily,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Personal Information",
                fontSize = 25.sp,
                fontFamily = KarlaTextFontFamily,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(vertical = 40.dp)
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp)
                ) {

                    Text(
                        "First Name",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    TextField(
                        value = viewModel.fName,
                        onValueChange = { viewModel.fName = it },
                        maxLines = 1,
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Green1,
                            unfocusedTextColor = Green1,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Text(
                        "Last Name",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    TextField(
                        value = viewModel.lName,
                        onValueChange = { viewModel.lName = it },
                        maxLines = 1,
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 20.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Green1,
                            unfocusedTextColor = Green1,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Text(
                        "Email",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    TextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.email = it },
                        maxLines = 1,
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 20.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Green1,
                            unfocusedTextColor = Green1,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                    )
                }

                Button(
                    {
                        if (viewModel.fName.isBlank() && viewModel.lName.isBlank() && viewModel.email.isBlank()) {
                            Toast.makeText(
                                context,
                                "Registration Unsuccessful !",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.submit()
                            navHostController.navigate(HomeDes.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFf4ce14)),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(0.5.dp, Color(0xFFc68338)),
//                    enabled = fName!="" && lName!="" && eMail!="",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 45.dp)
                ) {
                    Text(
                        "Register",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }

        }
    }

}

@Composable
fun ShowRegistrationFailedDialog(context: Context) {
    AlertDialog.Builder(context)
        .setTitle("Registration Failed")
        .setMessage("Please try again.")
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.height(50.dp)
        )
    }
}