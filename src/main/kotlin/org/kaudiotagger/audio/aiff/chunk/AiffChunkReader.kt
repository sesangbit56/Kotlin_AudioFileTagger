package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.iff.ChunkHeader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

abstract class AiffChunkReader {

    protected fun readChunkDataIntoBuffer(fc : FileChannel, chunkHeader : ChunkHeader) : ByteBuffer {
        val chunkData : ByteBuffer = ByteBuffer.allocateDirect(chunkHeader.getSize()!!.toInt())
        chunkData.order(ByteOrder.BIG_ENDIAN)
        fc.read(chunkData)
        chunkData.position(0)
        return chunkData
    }
}