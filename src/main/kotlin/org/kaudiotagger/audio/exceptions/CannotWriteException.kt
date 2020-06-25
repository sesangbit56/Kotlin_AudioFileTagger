package org.kaudiotagger.audio.exceptions

open class CannotWriteException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)