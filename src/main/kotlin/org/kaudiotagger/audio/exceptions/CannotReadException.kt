package org.kaudiotagger.audio.exceptions

open class CannotReadException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)