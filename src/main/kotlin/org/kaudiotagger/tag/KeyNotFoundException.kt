package org.kaudiotagger.tag

class KeyNotFoundException(override val message: String?, override val cause: Throwable?) : RuntimeException(message, cause) {
}