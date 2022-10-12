package com.xavidev.testessential

import org.hamcrest.Matchers.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertThat(4, equalTo(2 + 2))
    }

    @Test
    fun objectIsNotNull() {
        var obj: String? = null
        obj = "Hello"
        assertThat("The object shouldn't be null", obj, `is`(notNullValue()))
    }

    @Test
    fun hamcrestLogicalComparators() {
        assertThat("Javier", instanceOf(String::class.java))
        assertThat("Javier", isA(String::class.java))
        assertThat("Javier", anyOf(equalTo("Jav"), equalTo("Javier")))
        assertThat("Javier", allOf(startsWith("J"), endsWith("r")))
        assertThat("Javier", not(startsWith("a")))
        assertThat("Javier", not(equalTo("Javiers")))
    }

    @Test
    fun hamcrestStringComparators() {
        assertThat("Message", startsWith("M"))
        assertThat("Message", endsWith("e"))
        assertThat("Message", containsString("ess"))
        assertThat("Message", equalToIgnoringCase("messAGe"))
        assertThat("Me ss age", equalToIgnoringWhiteSpace("Me ss   age"))
    }

    @Test
    fun hamcrestNumberComparators() {
        assertThat(12, greaterThan(11))
        assertThat(12, greaterThanOrEqualTo(12))
        assertThat(12, lessThan(22))
        assertThat(12, lessThanOrEqualTo(13))
        assertThat(13.99, closeTo(13.98, 0.1))
    }

    @Test
    fun hamcrestMapsComparators() {
        val map = mapOf(1 to "Javier")
        assertThat(map, hasKey(1))
        assertThat(map, hasValue("Javier"))
        assertThat(map, hasEntry(1, "Javier"))
    }

    @Test
    fun hamcrestCollectionsComparators() {
        val list = listOf(1, 2, 3, 4, 5)
        assertThat("The list must contain the element 1", list, hasItem(3))
        assertThat(list, hasItems(3, 4))
    }
}