package com.xavidev.testessential.data.source.local.entity

import androidx.annotation.Nullable
import androidx.room.ColumnInfo


abstract class BaseEntity {
    @Nullable
    @ColumnInfo(name = "created_at")
    open var createdAt: Long? = null

    @Nullable
    @ColumnInfo(name = "updated_at")
    open var updatedAt: Long = System.currentTimeMillis()

    @Nullable
    @ColumnInfo(name = "deleted_at")
    open var deletedAt: Long? = null
}
