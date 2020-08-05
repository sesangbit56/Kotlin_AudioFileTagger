package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.aiff.AiffAudioHeader
import org.kaudiotagger.audio.aiff.AiffUtil
import org.kaudiotagger.audio.generic.Utils
import org.kaudiotagger.audio.iff.Chunk
import org.kaudiotagger.audio.iff.ChunkHeader
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.*

class CommentsChunk(override var chunkHeader: ChunkHeader,
                    override var chunkData : ByteBuffer,
                    private val aiffAudioHeader: AiffAudioHeader) : Chunk(chunkData, chunkHeader) {

    companion object {
        private val TIMESTAMP_LENGTH : Int = 4
        private val MARKERID_LENGTH : Int = 2
        private val COUNT_LENGTH : Int = 2
    }

    override fun readChunk(): Boolean {
        val numComments : Int = Utils.u(chunkData.getShort())

        for(i : Int in 0 until numComments) {
            //TODO getInt() 와 인수 int의 차이점이 무엇인지 확인 바람.
            val timestamp : Long = Utils.u(chunkData.getInt())
            // val jTimestamp : Date = AiffUtil.timestampToDate(timestamp)
            //TODO AiffUtil 정의합시다아
            val marker : Int = Utils.u(chunkData.getShort())
            val count : Int = Utils.u(chunkData.getShort())

            val text : String = Utils.getString(chunkData, 0, count, StandardCharsets.ISO_8859_1)
            if(count % 2 != 0) {
                chunkData.get()
            }
            aiffAudioHeader.addComments(text)
        }
        return true
    }
}