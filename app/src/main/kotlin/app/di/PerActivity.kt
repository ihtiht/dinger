package app.di

import javax.inject.Scope

@Retention(AnnotationRetention.RUNTIME)
@Scope
internal annotation class PerActivity
