package com.gosnomerKZ.presentation.main

import com.gosnomerKZ.presentation.core.Communication

interface PriceCommunication: Communication<String> {
    class Base: Communication.Abstract<String>(), PriceCommunication
}