package com.iia.couplechat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iia.couplechat.ui.NavGraphs
import com.iia.couplechat.ui.createchat.CreateChatViewModel
import com.iia.couplechat.ui.navigation.CoupleChatNavGraphDefaultAnimation
import com.iia.couplechat.ui.theme.CoupleChatTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        Log.d("TAG", "onCreate: ${auth.currentUser}")
        setContent {
            CoupleChatTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostEngine = rememberAnimatedNavHostEngine(
                        rootDefaultAnimations = CoupleChatNavGraphDefaultAnimation.COUPLE_CHAT_SLIDING
                    )
                    DestinationsNavHost(
                        navGraph = if (auth.currentUser == null)
                            NavGraphs.root
                        else
                            NavGraphs.chat,
                        engine = navHostEngine,
                        dependenciesContainerBuilder = {
                            dependency(NavGraphs.createChat){
                                val parentEntry = remember(navBackStackEntry){
                                    navController.getBackStackEntry(NavGraphs.createChat.route)
                                }
                                hiltViewModel<CreateChatViewModel>(parentEntry)
                            }

                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        Log.d("TAG", "onStart: ${auth.currentUser}")
    }
}
