package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.KarlaTextFontFamily



@Composable
fun Profile(navHostController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
    val fName = sharedPreferences.getString("firstName", "").toString()
    val lName = sharedPreferences.getString("lastName", "").toString()
    val email = sharedPreferences.getString("email", "").toString()

    val editor = sharedPreferences.edit()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(modifier = Modifier) {
            Header()
            Text(
                "Profile Information:",
                fontSize = 25.sp,
                fontFamily = KarlaTextFontFamily,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 25.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    Text(
                        "First Name :", fontSize = 20.sp, fontFamily = KarlaTextFontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        fName,
                        fontSize = 20.sp,
                        fontFamily = KarlaTextFontFamily,
                        modifier = Modifier
                            .border(0.5.dp, Color.Black, RoundedCornerShape(5.dp))
                            .size(height = 25.dp, width = 200.dp)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 5.dp)) {
                    Text(
                        "Last Name :", fontSize = 20.sp, fontFamily = KarlaTextFontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        lName, fontSize = 20.sp, fontFamily = KarlaTextFontFamily,
                        modifier = Modifier
                            .border(0.5.dp, Color.Black, RoundedCornerShape(5.dp))
                            .size(height = 25.dp, width = 200.dp)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 5.dp)) {

                    Text(
                        "E-mail :", fontSize = 20.sp, fontFamily = KarlaTextFontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        email, fontFamily = KarlaTextFontFamily, fontSize = 20.sp,
                        modifier = Modifier
                            .border(0.5.dp, Color.Black, RoundedCornerShape(5.dp))
                            .size(height = 25.dp, width = 200.dp)
                    )
                }
            }
        }
        Button(
            {
                editor.clear()
                editor.apply()
                Toast.makeText(context,"You have been logged out !",Toast.LENGTH_SHORT).show()
                navHostController.navigate(OnboardingDes.route)
            }, colors = ButtonDefaults.buttonColors(Color(0xFFf4ce14)),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.5.dp, Color(0xFFc68338)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 15.dp)
        ) {
            Text(
                "Log out",
                fontFamily = KarlaTextFontFamily,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}