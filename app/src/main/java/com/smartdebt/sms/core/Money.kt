package com.smartdebt.sms.core

import java.util.Locale
import kotlin.math.abs

/**
 * Amounts are stored as Long minor units (e.g. 1000.00 -> 100000) to avoid rounding errors.
 * DECIMALS is the number of minor-unit digits of the working currency.
 */
object Money {
    const val DECIMALS = 2

    private val FACTOR: Long = Math.pow(10.0, DECIMALS.toDouble()).toLong()

    fun fromMajor(major: String): Long? {
        val cleaned = major.trim().replace(',', '.')
        if (cleaned.isEmpty()) return null
        return try {
            java.math.BigDecimal(cleaned)
                .movePointRight(DECIMALS)
                .setScale(0, java.math.RoundingMode.UNNECESSARY)
                .longValueExact()
        } catch (e: ArithmeticException) {
            null
        } catch (e: NumberFormatException) {
            null
        }
    }

    fun format(minor: Long): String {
        val sign = if (minor < 0) "-" else ""
        val a = abs(minor)
        val whole = a / FACTOR
        val frac = a % FACTOR
        return if (DECIMALS == 0) {
            sign + String.format(Locale.US, "%,d", whole)
        } else {
            sign + String.format(Locale.US, "%,d.%0${DECIMALS}d", whole, frac)
        }
    }
}
