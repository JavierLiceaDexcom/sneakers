package com.xavidev.testessential.data

import com.xavidev.testessential.data.entity.*
import com.xavidev.testessential.data.entity.Currency
import java.util.*

object DummyData {
    const val CURRENCY_MXN_LOGO =
        "https://cdn.pixabay.com/photo/2012/04/10/23/24/mexico-26989__480.png"
    const val CURRENCY_USD_LOGO = "https://eabc-online.com/wp-content/uploads/2021/11/Flags.jpg"

    const val NIKE_LOGO = "https://nikecompanyblog.files.wordpress.com/2015/05/nike1.jpg"
    const val ADIDAS_LOGO =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Adidas_Logo.svg/2560px-Adidas_Logo.svg.png"
    const val CONVERSE_LOGO = "https://assets.simon.com/tenantlogos/13410.png"
    const val REEBOK_LOGO =
        "https://preview.thenewsmarket.com/Previews/RBOK/StillAssets/1920x1080/551064.png"
    const val NEW_BALANCE_LOGO =
        "https://logos-world.net/wp-content/uploads/2020/09/New-Balance-Emblem.png"
    const val PUMA_LOGO = "https://1000logos.net/wp-content/uploads/2017/05/PUMA-logo.jpg"
    const val YEEZY_LOGO =
        "https://i.pinimg.com/originals/53/96/37/5396379804fae3dffdcc887102f223e1.png"
    const val VANS_LOGO = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Vans-logo.svg/2560px-Vans-logo.svg.png"

    private val nikeImages = listOf(
        "https://media.gq.com/photos/61ae8b7ba5f2a6e20a63d30e/master/w_1280,c_limit/Nike-Killshot-2-sneakers.jpg",
        "https://media.gq.com/photos/61019f0a0bf452849a346d65/master/w_1280,c_limit/Nike-Air-Force-1-'07-sneakers.jpg",
        "https://media.gq.com/photos/6284ff88598bc90bcc76c28e/master/w_1280,c_limit/shoe-1.jpg",
        "https://media.gq.com/photos/6284ff86598bc90bcc76c28c/master/w_1280,c_limit/shoe-3.jpg",
        "https://media.gq.com/photos/6284ff8769b411d0f3704dfd/master/w_1280,c_limit/shoe-2.jpg",
    )

    private val adidasImages = listOf(
        "https://media.gq.com/photos/6284ff8869b411d0f3704dff/master/w_1280,c_limit/shoe-4.jpg",
        "https://media.gq.com/photos/61ae8b715f5bc36144ea4eb1/master/w_1280,c_limit/Adidas-Forum-84-low-sneakers.jpg",
        "https://media.gq.com/photos/6101a6cff1b49ea666d99112/master/w_1280,c_limit/Adidas-Samba-classic-sneakers.jpg",
        "https://media.gq.com/photos/6101a6cee01c89dcdd8730d7/master/w_1280,c_limit/Adidas-Originals-Stan-Smith-sneakers.jpg",
        "https://media.gq.com/photos/6101a6cf02037d9f64c0a5b9/master/w_1280,c_limit/Adidas-Originals-Superstar-sneakers.jpg"
    )

    private val converseImages = listOf(
        "https://media.gq.com/photos/6101bd633e2871dd07ed5dcd/master/w_1280,c_limit/Converse-Chuck-70-hi-sneakers.jpg",
        "https://media.gq.com/photos/61ae8b757439b36516f97481/master/w_1280,c_limit/Converse-one-star-sneakers.jpg",
        "https://media.gq.com/photos/6284ff89598bc90bcc76c290/master/w_1280,c_limit/shoe-5.jpg",
        "https://media.gq.com/photos/6284ff8abad17dc46fce8255/master/w_1280,c_limit/shoe-6.jpg",
        "https://media.gq.com/photos/6284ff89598bc90bcc76c290/master/w_1280,c_limit/shoe-5.jpg"
    )

    private val reebokImages = listOf(
        "https://media.gq.com/photos/6101c837e476e9883b7b0b92/master/w_1280,c_limit/Reebok-Club-C-sneakers.jpg",
        "https://media.gq.com/photos/6101c8383833ed9c41585ed0/master/w_1280,c_limit/Reebok-classic-leather-sneaker.jpg",
        "https://media.gq.com/photos/61ae8b7f5f5bc36144ea4ec1/master/w_1280,c_limit/Reebok-Zig-Kinetica-II-shoes.jpg",
        "https://media.gq.com/photos/6284ff8b22b45e91ce2497ea/master/w_1280,c_limit/shoe-9.jpg",
        "https://media.gq.com/photos/6284ff8a1e7f4591bdbc275f/master/w_1280,c_limit/shoe-8.jpg"
    )

    /*val sneakers = listOf(
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Nike Jordan One",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Nike",
                logo = NIKE_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.CASUAL
            ),
            colors = listOf(249235234, 127179213, 210040051),
            thumbnail = nikeImages[0],
            photos = nikeImages,
            price = 3200.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.MXN,
                region = "MX",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 0
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Nike Air Force One",
            sizes = listOf(25.5, 26.0, 27.0, 28.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Nike",
                logo = NIKE_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.CASUAL
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = nikeImages[0],
            photos = nikeImages,
            price = 2200.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.MXN,
                region = "MX",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 0
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Nike Low Flow",
            sizes = listOf(23.5, 24.0, 25.5, 26.0, 27.5),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Nike",
                logo = NIKE_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.SKATE
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = nikeImages[0],
            photos = nikeImages,
            price = 200.50,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 15
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Adidas Forum Classic",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Adidas",
                logo = ADIDAS_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.CASUAL
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = adidasImages[0],
            photos = adidasImages,
            price = 2650.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.MXN,
                region = "MX",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 0
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Puma Classic",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Puma",
                logo = PUMA_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.SKATE
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = adidasImages[1],
            photos = adidasImages,
            price = 1800.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.MXN,
                region = "MX",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 5
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Nike Air Sport ",
            sizes = listOf(22.5, 24.0, 26.5, 26.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Nike",
                logo = NIKE_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.SPORT
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = nikeImages[1],
            photos = nikeImages,
            price = 189.99,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 10
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Yeezy Mart Motion",
            sizes = listOf(22.5, 24.0, 25.5, 26.0, 27.5, 28.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Yeezy",
                logo = YEEZY_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.SPORT
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = adidasImages[2],
            photos = reebokImages,
            price = 320.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 10
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Adidas Superstar",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Adidas",
                logo = ADIDAS_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.CASUAL
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = adidasImages[3],
            photos = adidasImages,
            price = 1200.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.MXN,
                region = "MX",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 0
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Ultraboost 2022",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Adidas",
                logo = ADIDAS_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.CASUAL
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = converseImages[0],
            photos = adidasImages,
            price = 124.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 7
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Converse One Star",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Converse",
                logo = CONVERSE_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.SKATE
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = converseImages[1],
            photos = converseImages,
            price = 80.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 5
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Reebok Club C",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Reebok",
                logo = REEBOK_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.FORMAL
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = reebokImages[0],
            photos = reebokImages,
            price = 50.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 10
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "New Balance 99v5",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "New Balance",
                logo = NEW_BALANCE_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.TRAINING
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = reebokImages[1],
            photos = nikeImages,
            price = 322.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 20
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Vans \"Old School\" classic",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Vans",
                logo = VANS_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.SKATE
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = converseImages[2],
            photos = converseImages,
            price = 850.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.MXN,
                region = "MX",
                icon = CURRENCY_MXN_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 6
        ),
        Sneaker(
            id = UUID.randomUUID().toString(),
            model = "Puma \"Wild Rider\" rollin",
            sizes = listOf(25.5, 26.0, 26.5, 27.0),
            brand = Brand(
                id = UUID.randomUUID().toString(),
                name = "Puma",
                logo = PUMA_LOGO
            ),
            type = Type(
                id = UUID.randomUUID().toString(),
                name = SneakerType.TRAINING
            ),
            colors = listOf(255125147, 241698214),
            thumbnail = reebokImages[3],
            photos = converseImages,
            price = 100.0,
            currency = Currency(
                id = UUID.randomUUID().toString(),
                name = CurrencyType.USD,
                region = "US",
                icon = CURRENCY_USD_LOGO
            ),
            relatedIds = listOf(),
            discountPercentage = 15
        ),
    )*/
}