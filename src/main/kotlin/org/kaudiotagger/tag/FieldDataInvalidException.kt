package org.kaudiotagger.tag

class FieldDataInvalidException (override val message: String?, override val cause: Throwable?) : TagException(message, cause)