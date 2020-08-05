package org.kaudiotagger.audio.aiff

import org.kaudiotagger.audio.exceptions.CannotReadException
import org.kaudiotagger.audio.generic.Utils
import org.kaudiotagger.audio.iff.IffHeaderChunk.Companion.HEADER_LENGTH
import org.kaudiotagger.audio.iff.IffHeaderChunk.Companion.TYPE_LENGTH
import org.kaudiotagger.logging.Hex
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder.BIG_ENDIAN
import java.nio.channels.FileChannel
import java.util.logging.Logger
import org.kaudiotagger.audio.aiff.AiffType.AIFF
import org.kaudiotagger.audio.aiff.AiffType.AIFC

class AiffFileHeader {

    companion object {
        val FORM : String = "FORM"
        val logger : Logger = Logger.getLogger("org.kaudiotagger.audio.aiff.AudioFileHeader")
    }

    fun readHeader(fc : FileChannel, aiffAudioHeader : AiffAudioHeader, fileName : String) : Long {
        val headerData : ByteBuffer = ByteBuffer.allocateDirect(HEADER_LENGTH)
        headerData.order(BIG_ENDIAN)
        val bytesRead : Int = fc.read(headerData)
        headerData.position(0)

        if(bytesRead < HEADER_LENGTH) {
            throw IOException("$fileName AIFF:Unable to read required umber of databytes read:$bytesRead:required:$HEADER_LENGTH")
        }

        val signature : String = Utils.readFourBytesAsChars(headerData)
        if(FORM.equals(signature)) {
            //TODO 효율성?
            val chunkSize : Long = headerData.getInt().toLong()
            logger.severe("$fileName Reading AIFF header size:${Hex.asDecAndHex(chunkSize)}")

            readFileType(headerData, aiffAudioHeader)

            return chunkSize - TYPE_LENGTH
        } else {
            throw CannotReadException("$fileName Not an AIFF file: incorrect signature $signature")
        }
    }

    private fun readFileType(bytes : ByteBuffer, aiffAudioHeader: AiffAudioHeader) {
        val type : String = Utils.readFourBytesAsChars(bytes)
        if(AIFF.code.equals(type)) {
            aiffAudioHeader.setFileType(AIFF)
        } else if (AIFC.code.equals(type)) {
            aiffAudioHeader.setFileType(AIFC)
        } else {
            throw CannotReadException("Invalid AIFF file: Incorrect file type info $type")
        }
    }
}