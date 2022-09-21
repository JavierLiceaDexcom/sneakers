package com.xavidev.testessential.data

import com.xavidev.testessential.data.source.local.entity.*
import com.xavidev.testessential.data.source.local.entity.Currency
import java.util.*

object DatabaseTestUtil {

    val currentTime = System.currentTimeMillis()

    // Brand utils
    fun createBrand() =
        Brand(
            id = "0d4f8df4-2a3f-11ed-a261-0242ac120002",
            name = "Nike",
            logo = "https://nikecompanyblog.files.wordpress.com/2015/05/nike1.jpg",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        }

    fun createBrandList() = listOf(
        Brand(
            id = "0d4f8df4-2a3f-11ed-a261-0242ac120002",
            name = "Nike",
            logo = "https://nikecompanyblog.files.wordpress.com/2015/05/nike1.jpg",

            ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f90ba-2a3f-11ed-a261-0242ac120002",
            name = "Adidas",
            logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Adidas_Logo.svg/2560px-Adidas_Logo.svg.png",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f91fa-2a3f-11ed-a261-0242ac120002",
            name = "Converse",
            logo = "https://assets.simon.com/tenantlogos/13410.png",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f9312-2a3f-11ed-a261-0242ac120002",
            name = "New Balance",
            logo = "https://logos-world.net/wp-content/uploads/2020/09/New-Balance-Emblem.png",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f9434-2a3f-11ed-a261-0242ac120002",
            name = "Puma",
            logo = "https://1000logos.net/wp-content/uploads/2017/05/PUMA-logo.jpg",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f9542-2a3f-11ed-a261-0242ac120002",
            name = "Reebok",
            logo = "https://preview.thenewsmarket.com/Previews/RBOK/StillAssets/1920x1080/551064.png",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f9646-2a3f-11ed-a261-0242ac120002",
            name = "Yeezy",
            logo = "https://i.pinimg.com/originals/53/96/37/5396379804fae3dffdcc887102f223e1.png",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Brand(
            id = "0d4f99c0-2a3f-11ed-a261-0242ac120002",
            name = "Vans",
            logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Vans-logo.svg/2560px-Vans-logo.svg.png",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        }
    )

    // Sneakers utils
    fun createSneaker() = Sneaker(
        id = "fe8b8e2c-2272-11ed-861d-0242ac120002",
        brandId = "0d4f8df4-2a3f-11ed-a261-0242ac120002",
        model = "Air Jordan 1 Mid",
        thumbnail = "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/d81fee2a-e014-4fa1-9f6c-1870e8554a07/calzado-air-jordan-1-mid-RRTg1P1y.png",
        price = 2999.0,
        photosId = "0d4fa76c-2a3f-11ed-a261-0242ac120002",
        sizes = listOf(23.5, 25.0, 27.0),
        discountPercentage = 0,
        colors = listOf("#F5B041", "#F4F6F7"),
        typeId = "0d4fb126-2a3f-11ed-a261-0242ac120002",
        currencyId = "0d4f9eca-2a3f-11ed-a261-0242ac120002",
        favorite = false,
        inCart = false,
    ).apply {
        createdAt = currentTime
        updatedAt = currentTime
        deletedAt = null
    }

    fun createSneakersList(): List<Sneaker> = listOf(
        Sneaker(
            id = "fe8b8e2c-2272-11ed-861d-0242ac120002",
            brandId = "0d4f8df4-2a3f-11ed-a261-0242ac120002",
            model = "Air Jordan 1 Mid",
            thumbnail = "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/d81fee2a-e014-4fa1-9f6c-1870e8554a07/calzado-air-jordan-1-mid-RRTg1P1y.png",
            price = 2999.0,
            photosId = "0d4fa76c-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(23.5, 25.0, 27.0),
            discountPercentage = 0,
            colors = listOf("#F5B041", "#F4F6F7"),
            typeId = "0d4fb126-2a3f-11ed-a261-0242ac120002",
            currencyId = "0d4f9eca-2a3f-11ed-a261-0242ac120002",
            inCart = false,
            favorite = false,
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Sneaker(
            id = "0d4fb22a-2a3f-11ed-a261-0242ac120002",
            brandId = "0d4f90ba-2a3f-11ed-a261-0242ac120002",
            model = "Forum Low",
            thumbnail = "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/09c5ea6df1bd4be6baaaac5e003e7047_9366/Tenis_Forum_Low_Blanco_FY7756_01_standard.jpg",
            price = 2399.0,
            photosId = "0d4fa870-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(24.5, 25.0, 25.5, 26.0, 27.0),
            discountPercentage = 5,
            colors = listOf("#3498DB", "#2C3E50", "#F4D03F", "#52BE80"),
            typeId = "0d4f9ac4-2a3f-11ed-a261-0242ac120002",
            currencyId = "0d4f9eca-2a3f-11ed-a261-0242ac120002",
            inCart = false,
            favorite = false,
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Sneaker(
            id = "0d4fb32e-2a3f-11ed-a261-0242ac120002",
            brandId = "0d4f91fa-2a3f-11ed-a261-0242ac120002",
            model = "Chuck Taylor All Star in Cotton Boot",
            thumbnail = "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-1.jpg",
            price = 1599.0,
            photosId = "0d4fa974-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(24.5, 25.0, 25.5, 26.0),
            discountPercentage = 10,
            colors = listOf("#1A5276", "#F8F9F9", "#CB4335"),
            typeId = "0d4f9ac4-2a3f-11ed-a261-0242ac120002",
            currencyId = "0d4f9eca-2a3f-11ed-a261-0242ac120002",
            inCart = false,
            favorite = false,
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Sneaker(
            id = "0d4fb428-2a3f-11ed-a261-0242ac120002",
            brandId = "0d4f9312-2a3f-11ed-a261-0242ac120002",
            model = "550 'White Green' BB550WT1",
            thumbnail = "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/main-square_e68f1560-23b5-4c0d-928b-45dff16bda86_1296x.jpg?v=1656304409",
            price = 198.0,
            photosId = "0d4faa78-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(24.5, 25.5, 26.0),
            discountPercentage = 0,
            colors = listOf("#E5E7E9", "#7DCEA0"),
            typeId = "0d4f9ac4-2a3f-11ed-a261-0242ac120002",
            currencyId = "0d4f9fce-2a3f-11ed-a261-0242ac120002",
            inCart = false,
            favorite = false,
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        }
    )

    fun createSneakerComplete(): SneakerComplete = SneakerComplete(
        id = "fe8b8e2c-2272-11ed-861d-0242ac120002",
        brand = "Nike",
        model = "Air Jordan 1 Mid",
        thumbnail = "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/d81fee2a-e014-4fa1-9f6c-1870e8554a07/calzado-air-jordan-1-mid-RRTg1P1y.png",
        price = 2999.0,
        photosId = "0d4fa76c-2a3f-11ed-a261-0242ac120002",
        sizes = listOf(23.5, 25.0, 27.0),
        discountPercentage = 0,
        colors = listOf("#F5B041", "#F4F6F7"),
        type = "Jordan",
        currency = "MXN",
        inCart = false,
        favorite = false,
    )

    fun createSneakerCompletesList(): List<SneakerComplete> = listOf(
        SneakerComplete(
            id = "fe8b8e2c-2272-11ed-861d-0242ac120002",
            brand = "Nike",
            model = "Air Jordan 1 Mid",
            thumbnail = "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/d81fee2a-e014-4fa1-9f6c-1870e8554a07/calzado-air-jordan-1-mid-RRTg1P1y.png",
            price = 2999.0,
            photosId = "0d4fa76c-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(23.5, 25.0, 27.0),
            discountPercentage = 0,
            colors = listOf("#F5B041", "#F4F6F7"),
            type = "Jordan",
            currency = "MXN",
            inCart = false,
            favorite = false,
        ),
        SneakerComplete(
            id = "0d4fb22a-2a3f-11ed-a261-0242ac120002",
            brand = "Adidas",
            model = "Forum Low",
            thumbnail = "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/09c5ea6df1bd4be6baaaac5e003e7047_9366/Tenis_Forum_Low_Blanco_FY7756_01_standard.jpg",
            price = 2399.0,
            photosId = "0d4fa870-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(24.5, 25.0, 25.5, 26.0, 27.0),
            discountPercentage = 5,
            colors = listOf("#3498DB", "#2C3E50", "#F4D03F", "#52BE80"),
            type = "Casual",
            currency = "MXN",
            inCart = false,
            favorite = false,
        ),
        SneakerComplete(
            id = "0d4fb32e-2a3f-11ed-a261-0242ac120002",
            brand = "Converse",
            model = "Chuck Taylor All Star in Cotton Boot",
            thumbnail = "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-1.jpg",
            price = 1599.0,
            photosId = "0d4fa974-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(24.5, 25.0, 25.5, 26.0),
            discountPercentage = 10,
            colors = listOf("#1A5276", "#F8F9F9", "#CB4335"),
            type = "Casual",
            currency = "MXN",
            inCart = false,
            favorite = false,
        ),
        SneakerComplete(
            id = "0d4fb428-2a3f-11ed-a261-0242ac120002",
            brand = "New Balance",
            model = "550 'White Green' BB550WT1",
            thumbnail = "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/main-square_e68f1560-23b5-4c0d-928b-45dff16bda86_1296x.jpg?v=1656304409",
            price = 198.0,
            photosId = "0d4faa78-2a3f-11ed-a261-0242ac120002",
            sizes = listOf(24.5, 25.5, 26.0),
            discountPercentage = 0,
            colors = listOf("#E5E7E9", "#7DCEA0"),
            type = "Casual",
            currency = "USD",
            inCart = false,
            favorite = false,
        )
    )

    // Currency utils
    fun createCurrenciesList() = listOf(
        Currency(
            id = "0d4f9eca-2a3f-11ed-a261-0242ac120002",
            name = "Mexican Peso",
            region = "MX",
            icon = "https://cdn.pixabay.com/photo/2012/04/10/23/24/mexico-26989__480.png",
            abbreviation = "MXN",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        },
        Currency(
            id = "0d4f9fce-2a3f-11ed-a261-0242ac120002",
            name = "Dollar",
            region = "US",
            icon = "https://eabc-online.com/wp-content/uploads/2021/11/Flags.jpg",
            abbreviation = "USD",
        ).apply {
            createdAt = currentTime
            updatedAt = currentTime
            deletedAt = null
        }
    )

    //Types utils
    fun createSneakerTypesList() = listOf(
        Type(
            id = "0d4f9ac4-2a3f-11ed-a261-0242ac120002",
            name = "Casual"
        ),
        Type(
            id = "0d4f9bc8-2a3f-11ed-a261-0242ac120002",
            name = "Basketball"
        ),
        Type(
            id = "0d4f9cc2-2a3f-11ed-a261-0242ac120002",
            name = "Training"
        ),
        Type(
            id = "0d4f9dd0-2a3f-11ed-a261-0242ac120002",
            name = "Skate"
        ),
        Type(
            id = "0d4fb126-2a3f-11ed-a261-0242ac120002",
            name = "Jordan"
        )
    )

    // Sneaker images utils
    fun createSneakerImagesList() = listOf(
        Images(
            id = "0d4fa76c-2a3f-11ed-a261-0242ac120002",
            images = listOf(
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/6958211e-0d3c-47d8-a285-21ef9db34359/calzado-air-jordan-1-mid-RRTg1P1y.png",
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/401d2559-c045-4ed4-8131-9c9c298747de/calzado-air-jordan-1-mid-RRTg1P1y.png",
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/5182710d-94fd-4dd7-ba6c-49f373857078/calzado-air-jordan-1-mid-RRTg1P1y.png",
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/3e58e9ed-2327-4233-8214-e44f8fe44f53/calzado-air-jordan-1-mid-RRTg1P1y.png",
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/ebdb99c2-f265-425d-9c0c-db91a18ef82a/calzado-air-jordan-1-mid-RRTg1P1y.png",
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/56ccc74d-2744-45a1-a820-019a507ce639/calzado-air-jordan-1-mid-RRTg1P1y.png",
                "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/fd9a6dd7-fc1b-46df-b730-ba1b646fc4ac/calzado-air-jordan-1-mid-RRTg1P1y.png"
            )
        ),
        Images(
            id = "0d4fa870-2a3f-11ed-a261-0242ac120002",
            images = listOf(
                "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/ff82213b88c74ac5a0cbac5e004bd8e3_9366/Tenis_Forum_Low_Blanco_FY7756_02_standard_hover.jpg",
                "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/4cb5876afbae4250a787ac5e00365cd3_9366/Tenis_Forum_Low_Blanco_FY7756_03_standard.jpg",
                "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f0924c964ace43c78dd1ac5e00377f5e_9366/Tenis_Forum_Low_Blanco_FY7756_04_standard.jpg",
                "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/42c18826b6184120bae3ac5e003b1e30_9366/Tenis_Forum_Low_Blanco_FY7756_05_standard.jpg",
                "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/8b8c9a7179584904a304ac5e00373bb4_9366/Tenis_Forum_Low_Blanco_FY7756_09_standard.jpg",
                "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/2288761a07d04bc0a323ac5e003a3d77_9366/Tenis_Forum_Low_Blanco_FY7756_41_detail.jpg"
            )
        ),
        Images(
            id = "0d4fa974-2a3f-11ed-a261-0242ac120002",
            images = listOf(
                "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-2.jpg",
                "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-3.jpg",
                "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-4.jpg",
                "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-5.jpg",
                "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-7.jpg",
                "https://converse.com.mx/media/catalog/product/c/o/converse-chuck-taylor-algodon-caballero-bota-azul-a00480c-6.jpg"
            ),
        ),
        Images(
            id = "0d4faa78-2a3f-11ed-a261-0242ac120002",
            images = listOf(
                "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/1_300dc065-cce9-4820-afb8-63dd2791cd18_1296x.jpg?v=1656304409",
                "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/10_d6dbf25b-b2a0-4906-850e-0729bdf9fd2c_1296x.jpg?v=1656304409",
                "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/2_b11fde50-35d2-4fa3-9339-e26b2031c37d_1296x.jpg?v=1656304409",
                "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/3_7738c849-cae6-48a6-ac71-25ab5e73601b_1296x.jpg?v=1656304409",
                "https://cdn.shopify.com/s/files/1/0603/3031/1875/products/4_3eb48baf-9a79-4a57-8e18-e42b84459b4c_1296x.jpg?v=1656304409"
            )
        )
    )
}