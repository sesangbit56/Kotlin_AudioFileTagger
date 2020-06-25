package org.kaudiotagger.audio.exceptions

import java.io.IOException

class UnableToModifyFileException(override val message: String?) : IOException(message) {
}