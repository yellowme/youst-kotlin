package mx.yellowme.youst.playground.nemo.navigator

import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.nemo.NemoActivity
import mx.yellowme.youst.playground.shared.*

class CommonNavigator(private val activity: BaseActivity) : BaseNavigator {

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
        //TODO: Add animations
        completion.invoke()
    }
}
