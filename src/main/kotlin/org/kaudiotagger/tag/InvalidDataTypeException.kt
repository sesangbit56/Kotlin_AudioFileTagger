package org.kaudiotagger.tag

class InvalidDataTypeException(override val message: String?, override val cause: Throwable?) : InvalidTagException(message, cause)