package com.smartdebt.sms

import com.smartdebt.sms.core.Money
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MoneyTest {
    @Test fun parsesWholeAndFraction() {
        assertEquals(100000L, Money.fromMajor("1000"))
        assertEquals(30050L, Money.fromMajor("300.5"))
        assertEquals(30050L, Money.fromMajor("300,5"))
    }

    @Test fun rejectsTooManyDecimalsAndGarbage() {
        assertNull(Money.fromMajor("1.005"))
        assertNull(Money.fromMajor("abc"))
        assertNull(Money.fromMajor(""))
    }

    @Test fun formatsMinorUnits() {
        assertEquals("1,000.00", Money.format(100000L))
        assertEquals("0.05", Money.format(5L))
        assertEquals("-2.50", Money.format(-250L))
    }
}
