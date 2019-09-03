package mx.yellowme.youst.miniapps.domain

import java.lang.RuntimeException

class Chord(val name: String) {
    companion object {
        fun all(): List<Chord> {
            return arrayListOf(
                Chord("A"),
                Chord("Am"),
                Chord("A7"),
                Chord("B"),
                Chord("Bm"),
                Chord("B7"),
                Chord("C"),
                Chord("Cm"),
                Chord("C7"),
                Chord("D"),
                Chord("Dm"),
                Chord("D7"),
                Chord("E"),
                Chord("Em"),
                Chord("E7"),
                Chord("F"),
                Chord("Fm"),
                Chord("F7"),
                Chord("G"),
                Chord("Gm"),
                Chord("G7")
            )
        }

        fun transform(chord: Chord): Chord {
            return when(chord.name) {
                "A" -> Chord("Em")
                "Am" -> Chord("E")
                "A7" -> Chord("??") //TODO: Add proper chord
                "B" -> Chord("F#")
                "Bm" -> Chord("F#m")
                "B7" -> Chord("Fmaj7")
                "C" -> Chord("G")
                "Cm" -> Chord("Fm")
                "C7" -> Chord("G7")
                "D" -> Chord("A")
                "Dm" -> Chord("Am")
                "D7" -> Chord("??") //TODO: Add proper chord
                "E" -> Chord("B")
                "Em" -> Chord("Bm")
                "E7" -> Chord("B7")
                "F" -> Chord("??") //TODO: Add proper chord
                "Fm" -> Chord("A#m")
                "F7" -> Chord("??") //TODO: Add proper chord
                "G" -> Chord("D")
                "Gm" -> Chord("Dm")
                "G7" -> Chord("D7")
                else -> throw RuntimeException("Unsupported guitar chord")
            }
        }
    }
}
