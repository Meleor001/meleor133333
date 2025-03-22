package com.gosnomerKZ.numbers

//простой номер на выбор 465FRJ04
class BaseNumber : StateNumber {
    override fun getPriceMrp() = 2.8f
    override fun validate(value: String): Boolean {
        return !checkLetters(value)
    }
}

//не перечисленные здесь простые сочетания цифр + одинаковые буквы
//179MMM08
class BaseWithLetters : StateNumber {
    override fun getPriceMrp() = 57f

    override fun validate(value: String) = checkLetters(value)
}