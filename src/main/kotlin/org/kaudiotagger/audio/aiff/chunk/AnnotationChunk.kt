package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.aiff.AiffAudioHeader
import org.kaudiotagger.audio.iff.ChunkHeader
import java.nio.ByteBuffer

class AnnotationChunk(override var chunkHeader: ChunkHeader,
                      override var chunkData: ByteBuffer,
                      override val aiffAudioHeader: AiffAudioHeader) : TextChunk(chunkHeader, chunkData, aiffAudioHeader) {
    override fun readChunk(): Boolean {
        aiffAudioHeader.addAnnotations(readChunkText())
        return true
    }
}