package org.kaudiotagger.tag

open class InvalidTagException(override val message: String?, override val cause: Throwable?): TagException(message, cause)