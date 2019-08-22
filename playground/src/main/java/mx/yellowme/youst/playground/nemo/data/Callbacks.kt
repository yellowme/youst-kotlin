package mx.yellowme.youst.playground.nemo.data

interface ServerCallback {
    fun onServerError(message: String)
}

interface SimpleCallback : ServerCallback {
    fun onSuccess()
}

interface SingleItemCallback<Item> : ServerCallback {
    fun onLoad(item: Item?)
}
