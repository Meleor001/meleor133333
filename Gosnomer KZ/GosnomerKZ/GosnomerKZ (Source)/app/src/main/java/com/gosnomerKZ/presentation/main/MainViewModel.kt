package com.gosnomerKZ.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.firebase.database.DatabaseReference
import com.gosnomerKZ.numbers.BaseNumber
import com.gosnomerKZ.numbers.NumberFactory
import com.gosnomerKZ.numbers.StateNumber
import kotlin.collections.ArrayList

class MainViewModel(
    private val communication: MainCommunication,
    private val priceCommunication: PriceCommunication,
    private val factory: NumberFactory
) {

    private val list: ArrayList<StateNumber> = ArrayList()

    init {
        NumberFactory.NumberType.values().forEachIndexed { index, numberType ->
            list.add(factory.makeNumber(numberType))
        }
    }

    fun parse(value: String, list: List<StateNumber>): StateNumber {
        var result: StateNumber = BaseNumber()
        list.forEach {
            if (it.validate(value))
                result = it
        }
        return result
    }

    fun calculate(value: String, ){
        val state = parse(value, list)
        val price = state.getPrice()
        val priceMRP = state.getPriceMrp()

        val priceResult = if (price % 1.0f != 0f)
             String.format("%s", price)
        else
             String.format("%.0f", price)

        val mrpResult = if (priceMRP % 1.0f != 0f)
            String.format("%s", priceMRP)
        else
            String.format("%.0f", priceMRP)

        communication.map(Pair(priceResult, mrpResult))
    }

    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<Pair<String, String>>) {
        communication.observe(lifecycleOwner, observer)
    }

    fun priceObserve(lifecycleOwner: LifecycleOwner, observer: Observer<String>) {
        priceCommunication.observe(lifecycleOwner, observer)
    }

    fun updateMrp(database: DatabaseReference) {
        database.child("mrp").get().addOnSuccessListener {

            priceCommunication.map(it.value.toString())
            StateNumber.RATE = it.getValue(Float::class.java) as Float
        }
    }
}