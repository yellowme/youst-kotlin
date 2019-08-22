package mx.yellowme.youst.playground.nemo.navigation.matchers

import mx.yellowme.nemo.common.navigation.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.nemo.navigation.BaseNavigator
import mx.yellowme.youst.playground.nemo.data.UserFakeRepository
import mx.yellowme.youst.playground.domain.User
import mx.yellowme.youst.playground.nemo.navigation.hooks.Navigator

class HasLastName<Navigation>(
    private val repository: UserFakeRepository,
    private val navigation: Navigation
) : Navigator() where Navigation : BaseNavigator {

    override fun run() {
        repository.getData(false, object : AsyncSingleNavigator<User>(this) {
            override fun onLoad(item: User?) {
                continueFlowIf(!item?.lastName.isNullOrBlank())
            }
        })
    }

    override fun executeExit() {
        navigation.sendToUserWithoutAttribute()
    }

}
