package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioFile
import org.kaudiotagger.audio.exceptions.CannotReadException
import org.kaudiotagger.audio.exceptions.NoReadPermissionsException
import org.kaudiotagger.logging.ErrorMessage
import org.kaudiotagger.tag.Tag
import java.io.File
import java.io.RandomAccessFile
import java.lang.UnsupportedOperationException
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Level

abstract class AudioFileReader2 : AudioFileReader() {

    override fun read(f : File) : AudioFile {
        val path : Path = f.toPath()
        if(logger.isLoggable(Level.CONFIG)) {
            logger.config(ErrorMessage.GENERAL_READ.getMsg(path))
        }

        if(!Files.isReadable(path)) {
            logger.warning(Permissions.displayPermissions(path))
            throw NoReadPermissionsException(ErrorMessage.GENERAL_READ_FAILED_DO_NOT_HAVE_PERMISSION_TO_READ_FILE.getMsg(path))
        }

        if(f.length() <= MINUMUM_SIZE_FOR_VALID_AUDIO_FILE) {
            throw CannotReadException(ErrorMessage.GENERAL_READ_FAILED_FILE_TOO_SMALL.getMsg(path))
        }

        val info : GenericAudioHeader = getEncodingInfo(path)
        val tag : Tag = getTag(path)
        return AudioFile(f, info, tag)
    }

    protected abstract fun getEncodingInfo(path : Path) : GenericAudioHeader

    override fun getEncodingInfo(raf : RandomAccessFile) : GenericAudioHeader {
        throw UnsupportedOperationException("Old method not used in version 2")
    }

    protected abstract fun getTag(path : Path) : Tag

    override fun getTag(raf: RandomAccessFile): Tag {
        throw UnsupportedOperationException("Old method not used in version 2")
    }
}