package org.kaudiotagger.audio.exceptions

import java.io.IOException

class UnableToCreateFileException(override val message: String?) : IOException(message) {
}