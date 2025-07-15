package com.example.littlelemon.screens.profile

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.models.UserProfile
import com.example.littlelemon.navigation.OnboardingDes
import com.example.littlelemon.screens.onboarding.Header
import com.example.littlelemon.ui.theme.KarlaTextFontFamily
import com.example.littlelemon.ui.theme.MarkaziTextFontFamily


@Composable
fun Profile(navHostController: NavHostController, viewModel: ProfileViewModel) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(androidx.compose.foundation.layout.WindowInsets.systemBars.asPaddingValues()),
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
                        viewModel.profile.fName,
                        fontSize = 20.sp,
                        fontFamily = KarlaTextFontFamily,
                        modifier = Modifier
                            .border(0.5.dp, Color.Black, RoundedCornerShape(5.dp))
                            .size(height = 25.dp, width = 200.dp)
                            .padding(horizontal = 2.dp)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 5.dp)) {
                    Text(
                        "Last Name :", fontSize = 20.sp, fontFamily = KarlaTextFontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        viewModel.profile.lName, fontSize = 20.sp, fontFamily = KarlaTextFontFamily,
                        modifier = Modifier
                            .border(0.5.dp, Color.Black, RoundedCornerShape(5.dp))
                            .size(height = 25.dp, width = 200.dp)
                            .padding(horizontal = 2.dp)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 5.dp)) {

                    Text(
                        "E-mail :", fontSize = 20.sp, fontFamily = KarlaTextFontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        viewModel.profile.email, fontFamily = KarlaTextFontFamily, fontSize = 20.sp,
                        modifier = Modifier
                            .border(0.5.dp, Color.Black, RoundedCornerShape(5.dp))
                            .size(height = 25.dp, width = 200.dp)
                            .padding(horizontal = 2.dp)
                    )
                }
            }
        }
        if(viewModel.alertDialog){
            LogoutConfirmation(
                confirm = {
                    viewModel.logout()
                    Toast.makeText(context,"You have been logged out !",Toast.LENGTH_SHORT).show()
                    navHostController.navigate(OnboardingDes.route)
                          },
                cancel = { viewModel.alertDialog = false }
            )
        }
        Button(
            {
                viewModel.alertDialog = true
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutConfirmation(confirm:()-> Unit,cancel:()-> Unit) {
    BasicAlertDialog(
        onDismissRequest = { cancel() }
    ) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Are you sure you want to logout ?", fontFamily = KarlaTextFontFamily, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.align(Alignment.End)) {
                    TextButton(onClick = { confirm() } ) {
                        Text("Confirm", fontFamily = MarkaziTextFontFamily, style = MaterialTheme.typography.titleMedium)
                    }
                    TextButton(onClick = { cancel() } ) {
                        Text("Cancel", fontFamily = MarkaziTextFontFamily, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }

    /*AlertDialog.Builder(context)
        .setTitle("Registration Failed")
        .setMessage("Please try again.")
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .show()*/
}