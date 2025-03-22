package com.gosnomerKZ.numbers

private val list = "001,002,003,004,005,006,007,008,009,777"

//цифры 001, 002, 003, 004, 005, 006, 007, 008, 009, 777
//001DSC01
class ThirdDigits : StateNumber {

    override fun getPriceMrp() = 228f

    override fun validate(value: String) =
        checkDigits(list, value)
}

//цифры 001, 002, 003, 004, 005, 006, 007, 008, 009, 777 + одинаковые буквы
class ThirdDigitsWithLetters : StateNumber {

    override fun getPriceMrp() = 285f

    override fun validate(value: String) =
        checkDigits(list, value) && checkLetters(value)
}