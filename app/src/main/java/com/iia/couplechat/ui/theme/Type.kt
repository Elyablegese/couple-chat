package com.iia.couplechat.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.iia.couplechat.R

// Set of Material typography styles to start with
val Rubik = FontFamily(
    listOf(
        Font(R.font.rubik_bold, FontWeight.Bold),
        Font(R.font.rubik_light, FontWeight.Light),
        Font(R.font.rubik_light_italic, FontWeight.W500),
        Font(R.font.rubik_medium, FontWeight.Medium),
        Font(R.font.rubik_regular),
        Font(R.font.rubik_semibold, FontWeight.SemiBold)
    )
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)