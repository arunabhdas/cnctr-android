package com.codesdk.cloudcnctr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codesdk.cloudcnctr.presentation.ui.theme.CloudCnctrTheme
import com.codesdk.cloudcnctr.presentation.ui.MainScreen
import com.codesdk.cloudcnctr.presentation.ui.TabOneScreen

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloudCnctrTheme {
                MainScreen()
            }
        }
    }
}