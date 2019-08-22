package mx.yellowme.youst.playground.nemo.navigation.fetchers

import mx.yellowme.nemo.common.navigation.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.nemo.navigation.hooks.Navigator
import mx.yellowme.youst.playground.nemo.navigation.BaseNavigator
import mx.yellowme.youst.playground.nemo.data.UserFakeRepository
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
