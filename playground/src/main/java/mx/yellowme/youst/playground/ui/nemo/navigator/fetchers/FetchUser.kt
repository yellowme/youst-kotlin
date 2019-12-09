package mx.yellowme.youst.playground.ui.nemo.navigator.fetchers

import mx.yellowme.youst.playground.ui.nemo.navigator.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.ui.nemo.navigator.hooks.Navigator
import mx.yellowme.youst.playground.ui.nemo.navigator.BaseHelm
import mx.yellowme.youst.playground.data.UserFakeRepository
import mx.yellowme.youst.playground.domain.User

class FetchUser<Navigation>(
    private val repository: UserFakeRepository,
    private val navigation: Navigation
) : Navigator() where Navigation : BaseHelm {

    override fun run() {
        repository.getData(true, object : AsyncSingleNavigator<User>(this) {
            override fun onLoad(item: User?) {
                continueFlowIf(item != null)
            }
        })
    }

    override fun executeExit() {
        navigation.sendToLogin()
    }

}
