package com.pajato.acc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test fun testMainAcivityAdd() {
        val actual = MainActivity.add(2, 2)
        assertEquals(4, actual)
    }
}
