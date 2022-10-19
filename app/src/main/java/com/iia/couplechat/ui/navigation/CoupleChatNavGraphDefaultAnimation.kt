package com.iia.couplechat.ui.navigation

import androidx.compose.animation.*
import com.ramcosta.composedestinations.animations.defaults.DestinationEnterTransition
import com.ramcosta.composedestinations.animations.defaults.DestinationExitTransition
import com.ramcosta.composedestinations.animations.defaults.NavGraphDefaultAnimationParams
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

@ExperimentalAnimationApi
class CoupleChatNavGraphDefaultAnimation(
    override val enterTransition: DestinationEnterTransition = DestinationEnterTransition { EnterTransition.None },
    override val exitTransition: DestinationExitTransition = DestinationExitTransition { ExitTransition.None },
    override val popEnterTransition: DestinationEnterTransition = enterTransition,
    override val popExitTransition: DestinationExitTransition = exitTransition
) : NavGraphDefaultAnimationParams {
    companion object {
        val COUPLE_CHAT_SLIDING by lazy {
            RootNavGraphDefaultAnimations(
                enterTransition = { slideInHorizontally { it } },
                exitTransition = { slideOutHorizontally { -it } },
                popEnterTransition = { slideInHorizontally { -it }},
                popExitTransition = { slideOutHorizontally { it } }
            )
        }
    }
}