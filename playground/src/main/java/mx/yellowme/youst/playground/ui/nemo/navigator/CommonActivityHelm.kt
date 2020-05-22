package mx.yellowme.youst.playground.ui.nemo.navigator

import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.ui.nemo.NemoActivity
import mx.yellowme.youst.playground.ui.nemo.screens.LoginScreen
import mx.yellowme.youst.playground.ui.nemo.screens.MainScreen
import mx.yellowme.youst.playground.ui.nemo.screens.MissingPaymentOptionScreen
import mx.yellowme.youst.playground.ui.nemo.screens.UnsupportedVersionScreen
import mx.yellowme.youst.playground.ui.nemo.screens.UserWithoutAttributeScreen

class CommonActivityHelm(private val activity: BaseActivity) :
    BaseHelm {
    override fun sendToLogin() {
        navigate {
            activity.launch<LoginScreen>(finishCaller = true)
        }
    }

    override fun sendToSplash() {
        navigate {
            activity.launch<NemoActivity>(finishCaller = true)
        }
    }

    override fun sendToUnsupportedVersion() {
        navigate {
            activity.launch<UnsupportedVersionScreen>(finishCaller = true)
        }
    }

    override fun sendToUserWithoutAttribute() {
        navigate {
            activity.launch<UserWithoutAttributeScreen>(finishCaller = true)
        }
    }

    override fun sendToMain() {
        navigate {
            activity.launch<MainScreen>(finishCaller = true)
        }
    }

    override fun sendToMissingPaymentOption() {
        navigate {
            activity.launch<MissingPaymentOptionScreen>(finishCaller = true)
        }
    }

    private fun navigate(completion: () -> Unit) {
        // TODO: Add animations
        completion.invoke()
    }
}
