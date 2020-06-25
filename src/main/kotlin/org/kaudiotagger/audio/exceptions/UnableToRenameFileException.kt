package org.kaudiotagger.audio.exceptions

import java.io.IOException

class UnableToRenameFileException(override val message: String?) : IOException(message) {
}