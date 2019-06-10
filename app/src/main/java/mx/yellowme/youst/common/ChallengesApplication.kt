package mx.yellowme.youst.common;

import android.app.Application;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;

class ChallengesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
    }
}
