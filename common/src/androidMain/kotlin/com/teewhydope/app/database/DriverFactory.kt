package com.teewhydope.app.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.teewhydope.database.ContactDatabase

actual class DriverFactory {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ContactDatabase.Schema, applicationContext, "contact.db")
    }
}
