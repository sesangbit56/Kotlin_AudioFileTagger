package org.kaudiotagger.audio.exceptions

class ModifyVetoException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)