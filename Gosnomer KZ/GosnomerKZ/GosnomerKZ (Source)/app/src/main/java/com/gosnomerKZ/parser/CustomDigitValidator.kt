package com.gosnomerKZ.parser

import ru.tinkoff.decoro.slots.SlotValidators

class CustomDigitValidator: SlotValidators.DigitValidator() {

    override fun validate(value: Char): Boolean {
        return Character.isDigit(value) && value in '0'..'1'
    }

}