package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioFile
import org.kaudiotagger.audio.exceptions.CannotReadException
import org.kaudiotagger.audio.exceptions.NoReadPermissionsException
import org.kaudiotagger.logging.ErrorMessage
import org.kaudiotagger.tag.Tag
import java.io.File
import java.io.RandomAccessFile
import java.nio.file.Files
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.Exception

abstract class AudioFileReader {

    companion object {
        val logger: Logger = Logger.getLogger("org.jaudiotagger.audio.generic")
        val MINUMUM_SIZE_FOR_VALID_AUDIO_FILE: Int = 100
    }

    protected abstract fun getEncodingInfo(raf : RandomAccessFile) : GenericAudioHeader

    protected abstract fun getTag(raf : RandomAccessFile) : Tag


    open fun read(f : File) : AudioFile {
        if(logger.isLoggable(Level.CONFIG)) {
            logger.config(ErrorMessage.GENERAL_READ.getMsg(f.absolutePath))
        }

        if(!Files.isReadable(f.toPath())) {
            logger.warning(Permissions.displayPermissions(f.toPath()))
            throw NoReadPermissionsException(ErrorMessage.GENERAL_READ_FAILED_DO_NOT_HAVE_PERMISSION_TO_READ_FILE.getMsg(f.toPath()))
        }

        var raf : RandomAccessFile? = null
        try {
            raf = RandomAccessFile(f, "r")
            raf.seek(0)

            var info : GenericAudioHeader = getEncodingInfo(raf)
            raf.seek(0)
            val tag : Tag = getTag(raf)
            return AudioFile(f, info, tag)
            //TODO 코틀린의 Exception과 java의 Exception은 차이가 있는가? 확인 바람
        } catch (cre : CannotReadException) {
            throw cre
        } catch (e : Exception) {
            logger.log(Level.SEVERE, ErrorMessage.GENERAL_READ.getMsg(f.absolutePath), e)
            throw CannotReadException("${f.absolutePath}:${e.message}", e)
        } finally {
            try {
                if(raf != null) {
                    raf.close()
                }
            } catch (ex : Exception) {
                logger.log(Level.WARNING, ErrorMessage.GENERAL_READ_FAILED_UNABLE_TO_CLOSE_RANDOM_ACCESS_FILE.getMsg(f.absolutePath))
            }
        }
    }

}

