package org.kaudiotagger.audio.exceptions

class NoReadPermissionsException(override val message: String?, override val cause: Throwable?) : CannotReadException(message, cause)