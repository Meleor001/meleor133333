package com.gosnomerKZ.numbers

//цифры 100, 111, 200, 222, 300, 333, 400, 444, 500, 555, 600, 666, 700, 800, 888, 900, 999
//200NCG15
private val list = "100,111,200,222,300,333,400,444,500,555,600,666,700,800,888,900,999"

class SecondDigits : StateNumber {

    override fun getPriceMrp() = 137f

    override fun validate(value: String) =
        checkDigits(list, value)

}

//цифры 100, 111, 200, 222, 300, 333, 400, 444, 500, 555, 600, 666, 700, 800, 888, 900, 999 + одинаковые буквы
//200NNN15
class SecondDigitsWithLetters : StateNumber {

    override fun getPriceMrp() = 194f

    override fun validate(value: String): Boolean {
        return checkDigits(list, value) && checkLetters(value)
    }
}