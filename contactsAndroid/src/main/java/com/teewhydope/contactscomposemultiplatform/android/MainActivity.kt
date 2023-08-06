package com.teewhydope.contactscomposemultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.teewhydope.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        App(
            darkTheme = isSystemInDarkTheme(),
            dynamicColor = true,
        )
    }
}