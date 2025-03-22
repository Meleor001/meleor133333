package com.gosnomerKZ.presentation.main

import com.gosnomerKZ.presentation.core.Communication

interface MainCommunication: Communication<Pair<String, String>> {
    class Base: Communication.Abstract<Pair<String, String>>(), MainCommunication
}