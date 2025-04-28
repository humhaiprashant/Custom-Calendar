package com.example.customcalendar.utils

val Int.isLeapYear: Boolean
    get() = (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)