package com.gosnomerKZ.parser

import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.slots.SlotValidators

class CustomParser : UnderscoreDigitSlotsParser() {
    override fun slotFromChar(character: Char): Slot {
        return when (character) {
            SLOT_STUB -> {
                slotFromUnderscoreCharacter()
            }
            '-' -> {
                Slot(null, SlotValidators.LetterValidator())
            }
            '+' -> {
                Slot(null, CustomDigitValidator())
            }
            else -> slotFromNonUnderscoredChar(character)
        }
    }
}