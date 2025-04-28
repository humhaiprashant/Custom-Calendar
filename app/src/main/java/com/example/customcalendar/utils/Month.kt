package com.example.customcalendar.utils

enum class Month(
    name: String,
    days: Int
) {
    January(
        name = "January",
        days = 31
    ),
    February(
        name = "February",
        days = 28
    ),
    March(
        name = "March",
        days = 31
    ),
    April(
        name = "April",
        days = 30
    ),
    May(
        name = "May",
        days = 31
    ),
    June(
        name = "June",
        days = 30
    ),
    July(
        name = "July",
        days = 31
    ),
    August(
        name = "August",
        days = 31
    ),
    September(
        name = "September",
        days = 30
    ),
    October(
        name = "October",
        days = 31
    ),
    November(
        name = "November",
        days = 30
    ),
    December(
        name = "December",
        days = 31
    );

    fun plus(monthCount: Int): Month {
        val amt = monthCount % 12
        return entries[(ordinal + (amt + 12)) % 12]
    }

    fun minus(monthCount: Int): Month {
        return plus(-(monthCount % 12))
    }
}