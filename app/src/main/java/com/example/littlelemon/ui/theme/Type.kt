package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import java.io.File

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(file = File("fonts/markazi_text_regular.ttf"), FontWeight.Normal)),
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(file = File("fonts/karla_regular"))),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
val MarkaziTextFontFamily = FontFamily(
    Font(R.font.markazi_text_regular, FontWeight.Normal),
    Font(R.font.markazi_text_regular, FontWeight.Medium),
    Font(R.font.markazi_text_regular, FontWeight.Bold),
    Font(R.font.markazi_text_regular, FontWeight.ExtraBold),
)

val KarlaTextFontFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_regular, FontWeight.Medium),
    Font(R.font.karla_regular, FontWeight.Bold),
    Font(R.font.karla_regular, FontWeight.ExtraBold)
)