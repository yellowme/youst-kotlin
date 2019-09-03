package mx.yellowme.youst.playground.nemo.navigator.matchers

import mx.yellowme.youst.playground.nemo.navigator.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.nemo.navigator.BaseNavigator
import mx.yellowme.youst.playground.data.UserFakeRepository
import mx.yellowme.youst.playground.domain.User
import mx.yellowme.youst.playground.nemo.navigator.hooks.Navigator

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
