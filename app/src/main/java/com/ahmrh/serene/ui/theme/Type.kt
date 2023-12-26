package com.ahmrh.serene.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ahmrh.serene.R

//val provider = GoogleFont.Provider(
//    providerAuthority = "com.google.android.gms.fonts",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)
//
//val merriweather = GoogleFont("Merriweather")
//val fontFamilyMerriweather = FontFamily(
//    Font(googleFont = merriweather, fontProvider = provider,
//        weight = FontWeight.Bold,)
//)
//
//val openSans = GoogleFont("Open Sans")
//val fontFamilyOpenSans = FontFamily(
//    Font(googleFont = openSans, fontProvider = provider)
//)

val openSansFamily = FontFamily(
    Font(R.font.opensans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.opensans_regular, FontWeight.Normal),
)

val merriweatherFamily = FontFamily(
    Font(R.font.merriweather_bold, FontWeight.Bold),
    Font(R.font.merriweather_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.merriweather_regular, FontWeight.Normal),
    Font(R.font.merriweather_italic, FontWeight.Normal, FontStyle.Italic),
)
// Set of Material typography styles to start with
val typography = Typography(
    // Display
    displayLarge = TextStyle(

        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(

        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(

        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // Headline
    headlineLarge = TextStyle(
        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),


    // Title
    titleLarge = TextStyle(
        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp

    ),
    titleMedium = TextStyle(
        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp

    ),
    titleSmall = TextStyle(
        fontFamily = merriweatherFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    // Label
    labelLarge = TextStyle(
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)