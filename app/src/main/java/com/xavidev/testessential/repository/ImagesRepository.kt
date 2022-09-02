package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.Images
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    suspend fun insertImages(images: List<Images>): Flow<Response<Unit>>
    suspend fun getSneakerImages(imagesId: String): Flow<Response<List<Images>>>
}