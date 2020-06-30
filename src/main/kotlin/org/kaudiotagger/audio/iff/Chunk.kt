package org.kaudiotagger.audio.iff

import java.nio.ByteBuffer

abstract class Chunk(protected var chunkData : ByteBuffer,
                     protected var chunkHeader : ChunkHeader) {

    abstract fun readChunk() : Boolean

}