package org.kaudiotagger.audio.aiff.chunk

import java.nio.ByteBuffer
import java.nio.channels.FileChannel

abstract class AiffChunkReader {

    protected fun readChunkDataIntoBuffer(fc : FileChannel, chunkHeader : ChunkHeader) {
        //TODO 정의합시다아
        val chunkData : ByteBuffer = ByteBuffer.allocateDirect(chunkHeader.size)
    }
}