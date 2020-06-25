package org.kaudiotagger.tag

class EmptyFrameException (override val message: String?, override val cause: Throwable?) : TagException(message, cause)