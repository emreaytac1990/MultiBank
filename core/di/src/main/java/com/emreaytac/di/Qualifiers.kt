package com.emreaytac.di

import javax.inject.Qualifier

interface Dispatchers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Main

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Default

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IO
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl
