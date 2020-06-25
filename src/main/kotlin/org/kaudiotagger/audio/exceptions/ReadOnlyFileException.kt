package org.kaudiotagger.audio.exceptions

class ReadOnlyFileException(override val message: String?, override val cause: Throwable?) : Exception(message, cause) {
}