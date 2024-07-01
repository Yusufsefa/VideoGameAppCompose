package com.yyusufsefa.videogameappcompose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.presentation.navigation.NavGraph
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val startDestination by viewModel.startDestination.collectAsState()

            SideEffect {
                window.statusBarColor = getColor(R.color.bg_home_screen)
                window.navigationBarColor = getColor(R.color.bg_home_screen)
            }

            VideoGameAppComposeTheme {
                if (startDestination != null) {
                    SideEffect {
                        window.statusBarColor = getColor(R.color.bg_home_screen)
                        window.navigationBarColor = getColor(R.color.bg_home_screen)
                    }

                    VideoGameAppComposeTheme {
                        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                            NavGraph(startDestination = startDestination!!)
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
