package com.teewhydope.app.database

import android.content.Context
import androidx.startup.Initializer

class SqlDelightInitialiser : Initializer<MyModule> {
    override fun create(context: Context): MyModule {
        applicationContext = context.applicationContext
        return MyModule
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}

lateinit var applicationContext: Context
    private set

object MyModule
