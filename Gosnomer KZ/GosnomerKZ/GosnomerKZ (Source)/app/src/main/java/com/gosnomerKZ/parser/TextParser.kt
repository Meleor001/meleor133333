package com.gosnomerKZ.parser

import android.widget.EditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

interface TextParser {

    fun createSlots(): Array<Slot>
    fun createMask(): MaskImpl
    fun createWatcher(): FormatWatcher
    fun setEditText(view: EditText)

    class Base : TextParser {

        override fun createSlots(): Array<Slot> =
            CustomParser().parseSlots("___---+_")

        override fun createMask(): MaskImpl =
            MaskImpl.createTerminated(createSlots())

        override fun createWatcher() =
            MaskFormatWatcher(createMask())

        override fun setEditText(view: EditText) =
            createWatcher().installOn(view)

    }

}