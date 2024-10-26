package com.joaovitormo.resumegenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.joaovitormo.resumegenerator.ui.screens.ResumeScreen
import com.joaovitormo.resumegenerator.ui.theme.ResumeGeneratorTheme
import com.joaovitormo.resumegenerator.ui.viewmodel.ResumeViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ResumeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ResumeViewModel::class.java]

        setContent {
            ResumeGeneratorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ResumeScreen(viewModel)
                }
            }
        }
    }
}




