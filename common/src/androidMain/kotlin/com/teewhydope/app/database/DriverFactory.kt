package com.teewhydope.app.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.teewhydope.database.ContactDatabase

actual class DriverFactory {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ContactDatabase.Schema, applicationContext, "contact.db")
    }
}
