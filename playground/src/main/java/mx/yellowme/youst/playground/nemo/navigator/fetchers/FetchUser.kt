package mx.yellowme.youst.playground.nemo.navigator.fetchers

import mx.yellowme.nemo.common.navigation.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.nemo.navigator.hooks.Navigator
import mx.yellowme.youst.playground.nemo.navigator.BaseNavigator
import mx.yellowme.youst.playground.data.UserFakeRepository
import mx.yellowme.youst.playground.domain.User

class FetchUser<Navigation>(
    private val repository: UserFakeRepository,
    private val navigation: Navigation
) : Navigator() where Navigation : BaseNavigator {

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
