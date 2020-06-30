package org.kaudiotagger.audio.iff

import org.kaudiotagger.audio.generic.Utils
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.util.logging.Logger

class IffHeaderChunk {
    companion object {
        val logger : Logger = Logger.getLogger("org.kaudiotagger.audio.iff")

        var SIGNATURE_LENGTH : Int = 4
        var SIZE_LENGTH : Int = 4
        var TYPE_LENGTH : Int = 4
        var HEADER_LENGTH : Int = SIGNATURE_LENGTH + SIZE_LENGTH + TYPE_LENGTH

        fun ensureOnEqualBoundary(raf : RandomAccessFile, chunkHeader: ChunkHeader) {
            if(Utils.isOddLength(chunkHeader.getSize()!!)) {
                if(raf.filePointer < raf.length()) {
                    logger.config("Skipping Byte because on odd boundary")
                    raf.skipBytes(1)
                }
            }
        }

        fun ensureOnEqualBoundary(fc : FileChannel, chunkHeader: ChunkHeader) {
            if(Utils.isOddLength(chunkHeader.getSize()!!)) {
                if(fc.position() < fc.size()) {
                    logger.config("Skipping Byte because on odd boundary")
                    fc.position(fc.position() + 1)
                }
            }
        }
    }
}