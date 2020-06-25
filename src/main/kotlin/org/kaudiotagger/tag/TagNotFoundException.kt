package org.kaudiotagger.tag

open class TagNotFoundException(override val message: String?, override val cause: Throwable?) : TagException(message, cause)