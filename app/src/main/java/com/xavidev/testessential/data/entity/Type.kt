package com.xavidev.testessential.data.entity

import java.io.Serializable

data class Type(val id: String, val name: SneakerType) : Serializable

enum class SneakerType(type: String) {
    SPORT("sport"), CASUAL("casual"), SKATE("skate"), TRAINING("training"), FORMAL("formal")
}
