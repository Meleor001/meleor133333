package com.gosnomerKZ.numbers

interface NumberFactory {
    fun makeNumber(type: NumberType): StateNumber

    class Base: NumberFactory {
        override fun makeNumber(type: NumberType) =
            when(type){
                NumberType.BASE -> BaseNumber()
                NumberType.BASE_WITH_LETTERS -> BaseWithLetters()
                NumberType.DIGITS -> Digits()
                NumberType.DIGITS_WITH_LETTERS -> DigitsWithLetters()
                NumberType.SECOND_DIGITS -> SecondDigits()
                NumberType.SECOND_DIGITS_WITH_LETTERS -> SecondDigitsWithLetters()
                NumberType.THIRD_DIGITS -> ThirdDigits()
                NumberType.THIRD_DIGITS_WITH_LETTERS -> ThirdDigitsWithLetters()
            }
    }

    enum class NumberType {
        BASE, BASE_WITH_LETTERS,
        DIGITS, DIGITS_WITH_LETTERS,
        SECOND_DIGITS, SECOND_DIGITS_WITH_LETTERS,
        THIRD_DIGITS, THIRD_DIGITS_WITH_LETTERS
    }
}

