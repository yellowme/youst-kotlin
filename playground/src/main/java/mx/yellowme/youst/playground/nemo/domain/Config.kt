package mx.yellowme.youst.playground.nemo.domain

class Config(var versionName: String, var versionCode: Int) {
    val hasSupportedVersion: Boolean
        get() {
            return versionCode > 10
        }
}
