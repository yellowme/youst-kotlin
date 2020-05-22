package mx.yellowme.youst.miniapps.ui.chords

import android.os.Bundle
import kotlinx.android.synthetic.main.chords.chordSelector
import kotlinx.android.synthetic.main.chords.selectedChordTextView
import kotlinx.android.synthetic.main.chords.selectedChordTitle
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.utils.HapticFeedbackHelper
import mx.yellowme.youst.miniapps.R
import mx.yellowme.youst.miniapps.domain.Chord

class ChordsActivity : BaseActivity() {
    override val layoutId = R.layout.chords

    private var currentChordPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateSelected(Chord.all().first())

        chordSelector.setOnProgressChangedListener { value ->
            val selectedPosition = value - 1
            if (currentChordPosition != selectedPosition) {
                currentChordPosition = selectedPosition
                updateSelected(Chord.all()[currentChordPosition])
            }
        }
    }

    private fun updateSelected(chord: Chord) {
        with(chord) {
            selectedChordTitle.text = getString(R.string.chord_title, name)
            selectedChordTextView.text = Chord.transform(this).name
            chordSelector.label = name
        }

        HapticFeedbackHelper.generateTouchFeedbackOn(this)
    }
}
