package mx.yellowme.youst.playground.domain

class Config(var versionName: String, var versionCode: Int) {
    val hasSupportedVersion: Boolean
        get() {
            return versionCode > 10
        }
}
