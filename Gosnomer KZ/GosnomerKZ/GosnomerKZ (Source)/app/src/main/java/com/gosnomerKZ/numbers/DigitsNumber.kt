package com.gosnomerKZ.numbers

//цифры 010, 020, 030, 040, 050, 060, 070, 077, 080, 090, 707
//020KSA05

private val list = "010,020,030,040,050,060,070,077,080,090,707"

class Digits : StateNumber {

    override fun getPriceMrp() = 57f

    override fun validate(value: String) =
        checkDigits(list, value)

}

//цифры 010, 020, 030, 040, 050, 060, 070, 077, 080, 090, 707 + одинаковые буквы
//020KKK05
class DigitsWithLetters : StateNumber {
    override fun getPriceMrp() = 114f
    override fun validate(value: String) =
        checkDigits(list, value) && checkLetters(value)
}