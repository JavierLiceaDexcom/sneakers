package com.xavidev.testessential

import com.google.common.truth.Truth.assertThat
import org.junit.Test

//Example of the use of truth library

class TruthTest {

    @Test
    fun booleanVerification() {
        val result = 12.50
        assertThat(result).isAtLeast(12.0)
        assertThat(result).isAtMost(13.0)
        assertThat(result).isEqualTo(12.5)
        assertThat(result).isNotEqualTo(12.0)
        assertThat(result).isGreaterThan(10.0)
        assertThat(result).isLessThan(18.0)
        assertThat(result).isWithin(0.01).of(12.505)
        assertThat(result).isNotWithin(0.001).of(12.00050)
    }

    @Test
    fun stringVerifications() {
        val result = "Hello Kotlin"
        assertThat(result).contains("Hello")
        assertThat(result).doesNotContain("Java")
        assertThat(result).startsWith("Hel")
        assertThat(result).endsWith("in")
        assertThat(result).hasLength(12)
        assertThat(result).ignoringCase().isEqualTo("hello kotlin")
    }

    @Test
    fun collectionVerifications() {
        val result = listOf("perro", "gato", "pájaro", "ballena", "elefante")
        val result2 = listOf("perro", "gato", "pájaro", "ballena", "elefante").sorted()
        val emptyList = listOf<String>()
        assertThat(result).contains("gato")
        assertThat(result).containsAnyOf("pájaro", "canguro")
        assertThat(result).containsAnyIn(listOf("ballena", "ratón", "murcielago"))
        assertThat(result).containsNoneOf("ratón", "murcielago")
        assertThat(result).containsNoneIn(listOf("ratón", "murcielago"))
        assertThat(result).containsAtLeast("perro", "elefante")
        assertThat(result).containsExactly("ballena", "pájaro", "gato", "perro", "elefante")
        assertThat(result).containsExactly("perro", "gato", "pájaro", "ballena", "elefante").inOrder()
        assertThat(result).containsNoDuplicates()
        assertThat(result2).isInOrder()
        assertThat(result2).hasSize(5)
        assertThat(emptyList).isEmpty()
        assertThat(result2).isNotEmpty()
    }
}