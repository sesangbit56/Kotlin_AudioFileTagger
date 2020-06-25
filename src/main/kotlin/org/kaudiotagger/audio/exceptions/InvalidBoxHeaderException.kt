package org.kaudiotagger.audio.exceptions

import java.lang.RuntimeException

class InvalidBoxHeaderException(override val message: String?) : RuntimeException(message) {
}