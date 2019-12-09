package mx.yellowme.youst.playground.ui.nemo.navigator.matchers

import mx.yellowme.youst.playground.ui.nemo.navigator.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.ui.nemo.navigator.BaseHelm
import mx.yellowme.youst.playground.data.UserFakeRepository
import mx.yellowme.youst.playground.domain.User
import mx.yellowme.youst.playground.ui.nemo.navigator.hooks.Navigator

class HasLastName<Navigation>(
    private val repository: UserFakeRepository,
    private val navigation: Navigation
) : Navigator() where Navigation : BaseHelm {

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
