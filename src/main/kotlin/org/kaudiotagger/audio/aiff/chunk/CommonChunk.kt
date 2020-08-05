package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.aiff.AiffAudioHeader
import org.kaudiotagger.audio.generic.Utils
import org.kaudiotagger.audio.iff.Chunk
import org.kaudiotagger.audio.iff.ChunkHeader
import java.nio.ByteBuffer

class CommonChunk(override var chunkData: ByteBuffer,
                  override var chunkHeader: ChunkHeader,
                  val aiffAudioHeader: AiffAudioHeader) : Chunk(chunkData, chunkHeader) {

    override fun readChunk(): Boolean {
        var numChannels : Int = Utils.u(chunkData.getShort())
        var numSamples : Long = chunkData.getInt().toLong()
        var bitsPerSample : Int = Utils.u(chunkData.getShort())
        //var sampleRate : Double = AiffUtil.read80BitDouble(chunkData)
        //TODO AiffUtil 정의의합다.

    }

}