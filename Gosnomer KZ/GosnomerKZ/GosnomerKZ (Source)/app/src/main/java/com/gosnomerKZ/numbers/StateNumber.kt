package com.gosnomerKZ.numbers

interface StateNumber {

    companion object {
        var RATE = 3063f
    }

    fun getPriceMrp(): Float //МРП
    fun getPrice() = getPriceMrp() * RATE //тнг
    fun validate(value: String): Boolean

    fun checkDigits(list: String, value: String): Boolean =
        list.split(',').any { it in value }

    fun checkLetters(value: String): Boolean {
        val letters = value.filter { it.isLetter() }
        val c = letters[0]
        val checked = letters.filter { current -> current == c }
        return checked.length == letters.length
    }

}