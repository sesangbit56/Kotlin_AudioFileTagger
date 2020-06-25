package org.kaudiotagger.audio.exceptions

class NoReadPermissionsException(override val message: String? = null, override val cause: Throwable? = null) : CannotReadException(message, cause)