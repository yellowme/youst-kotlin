package mx.yellowme.youst.miniapps.ui.chords

import android.os.Bundle
import kotlinx.android.synthetic.main.chords.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.miniapps.R
import mx.yellowme.youst.miniapps.domain.Chord

class ChordsActivity : BaseActivity() {

    override val layoutId = R.layout.chords

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateSelected(Chord.all().first())

        chordSelector.setOnProgressChangedListener { value ->
            updateSelected(Chord.all()[value - 1])
        }
    }

    private fun updateSelected(chord: Chord) {
        selectedChordTextView.text = Chord.transform(chord).name
        chordSelector.label = chord.name
    }

}
