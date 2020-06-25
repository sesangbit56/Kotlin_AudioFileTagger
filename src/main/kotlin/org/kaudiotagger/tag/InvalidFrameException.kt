package org.kaudiotagger.tag

open class InvalidFrameException(override val message: String?, override val cause: Throwable?) : InvalidTagException(message, cause)