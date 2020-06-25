package org.kaudiotagger.audio.exceptions

class NoWritePermissionsException(override val message: String?, override val cause: Throwable?) : CannotWriteException(message, cause)