package com.teewhydope.architecture.data.mapper

abstract class DomainToDataMapper<INPUT : Any, OUTPUT : Any> {

    fun toData(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DataMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class DataToDomainMapper<INPUT : Any, OUTPUT : Any> {

    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DataMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

class DataMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)
