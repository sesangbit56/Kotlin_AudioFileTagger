package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.aiff.AiffAudioHeader
import org.kaudiotagger.audio.generic.Utils
import org.kaudiotagger.audio.iff.Chunk
import org.kaudiotagger.audio.iff.ChunkHeader
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

//TODO 인수에 오버라이딩은 도대체 왜 필요한 것인지? 확인바람.
abstract class TextChunk(override var chunkHeader: ChunkHeader,
                         override var chunkData : ByteBuffer,
                         open val aiffAudioHeader: AiffAudioHeader) : Chunk(chunkData, chunkHeader) {
    fun readChunkText() : String = Utils.getString(chunkData, 0, chunkData.remaining(), StandardCharsets.ISO_8859_1)
}