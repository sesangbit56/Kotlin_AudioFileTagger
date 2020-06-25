package org.kaudiotagger.tag

class InvalidFrameIdentifierException(override val message: String?, override val cause: Throwable?) : InvalidFrameException(message, cause)