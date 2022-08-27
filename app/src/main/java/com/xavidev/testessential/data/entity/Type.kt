package com.xavidev.testessential.data.entity

data class Type(val id: String, val name: SneakerType)

enum class SneakerType(type: String) {
    SPORT("sport"), CASUAL("casual"), SKATE("skate"), TRAINING("training"), FORMAL("formal")
}
