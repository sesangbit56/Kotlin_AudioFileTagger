package org.kaudiotagger.audio.iff

import java.nio.ByteBuffer

//TODO 이것이 정말 맞는가?
abstract class Chunk(protected open var chunkData : ByteBuffer,
                     protected open var chunkHeader : ChunkHeader) {

    abstract fun readChunk() : Boolean

}