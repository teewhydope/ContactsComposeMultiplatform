package com.teewhydope.app.database

import com.squareup.sqldelight.db.SqlDriver
import com.teewhydope.database.ContactDatabase

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

class DatabaseFactory(
    private val driverFactory: DriverFactory,
) {
    fun createDatabase(): ContactDatabase {
        return ContactDatabase(driverFactory.createDriver())
    }
}
