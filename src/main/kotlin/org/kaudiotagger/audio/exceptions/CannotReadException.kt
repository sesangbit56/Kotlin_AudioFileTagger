package org.kaudiotagger.audio.exceptions

open class CannotReadException(override val message: String? = null, override val cause: Throwable? = null) : Exception(message, cause)