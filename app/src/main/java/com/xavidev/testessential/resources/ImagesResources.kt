package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.ImagesDao
import com.xavidev.testessential.data.entity.Images
import com.xavidev.testessential.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ImagesResources(private val imagesDao: ImagesDao) : ImagesRepository {
    override suspend fun insertImages(images: List<Images>): Flow<Response<Unit>> = flow {
        emit(Response.Loading())
        try {
            val response = imagesDao.populateImages(images)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getSneakerImages(imagesId: String): Flow<Response<List<Images>>> = flow {
        emit(Response.Loading())
        try {
            val response = imagesDao.getSneakerImages(imagesId)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}