package com.example.littlelemon

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.activity.ComponentActivity.MODE_PRIVATE
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.MarkaziTextFontFamily
import com.example.littlelemon.ui.theme.KarlaTextFontFamily


@Composable
fun Onboarding(navHostController: NavHostController, sharedPreferences: SharedPreferences) {

    var fName by remember {
        mutableStateOf("")
    }
    var lName by remember {
        mutableStateOf("")
    }
    var eMail by remember {
        mutableStateOf("")
    }


    val context = LocalContext.current


    Column(modifier = Modifier.statusBarsPadding()) {
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
        ) {
            Text(
                "Personal Information",
                fontSize = 25.sp,
                fontFamily = KarlaTextFontFamily,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(vertical = 45.dp)
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxHeight(0.6f)
                ) {

                    Text(
                        "First Name",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    TextField(
                        value = fName,
                        onValueChange = { value ->
                            fName = value
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        textStyle = TextStyle(
                            fontSize = 17.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 25.dp)
                            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                    )

                    Text(
                        "Last Name",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    TextField(
                        value = lName,
                        onValueChange = { value ->
                            lName = value
                        },
                        textStyle = TextStyle(
                            fontSize = 17.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 25.dp)
                            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                    )

                    Text(
                        "Email",
                        fontFamily = KarlaTextFontFamily,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    TextField(
                        value = eMail,
                        onValueChange = { value ->
                            eMail = value
                        },
                        textStyle = TextStyle(
                            fontSize = 17.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                    )
                }

                Button(
                    {
                        if (fName.isBlank() && lName.isBlank() && eMail.isBlank()) {
                            Toast.makeText(
                                context,
                                "Registration Unsuccessful !",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            sharedPreferences.edit(commit = true) {
                                putString("firstName", fName)
                                putString("lastName", lName)
                                putString("email", eMail)
                                putBoolean("userLogged", true)
                            }
                            navHostController.navigate(HomeDes.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFf4ce14)),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(0.5.dp, Color(0xFFc68338)),
//                    enabled = fName!="" && lName!="" && eMail!="",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 30.dp)
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
fun showRegistrationFailedDialog(context: Context) {
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