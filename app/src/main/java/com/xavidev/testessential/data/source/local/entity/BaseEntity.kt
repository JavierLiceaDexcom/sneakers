package com.xavidev.testessential.data.source.local.entity

interface BaseEntity {
    val createdAt: Long?
    val updatedAt: Long?
    val deletedAt: Long?
}
