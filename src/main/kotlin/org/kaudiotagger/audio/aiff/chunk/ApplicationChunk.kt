package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.aiff.AiffAudioHeader
import org.kaudiotagger.audio.generic.Utils
import org.kaudiotagger.audio.iff.Chunk
import org.kaudiotagger.audio.iff.ChunkHeader
import java.nio.ByteBuffer

class ApplicationChunk(override var chunkHeader: ChunkHeader,
                       override var chunkData: ByteBuffer,
                       open val aiffHeader: AiffAudioHeader) : Chunk(chunkData, chunkHeader) {
    companion object {
        private val SIGNATURE_PDOS : String = "pdos"
        private val SIGNATURE_STOC : String = "stoc"
    }

    override fun readChunk(): Boolean {
        val applicationSignature : String = Utils.readFourBytesAsChars(chunkData)
        var applicationName : String? = null

        if(SIGNATURE_STOC.equals(applicationSignature) || SIGNATURE_PDOS.equals(applicationSignature)) {
            applicationName = Utils.readPascalString(chunkData)
        }
        aiffHeader.addApplicationIdentifiers("$applicationSignature:$applicationName")

        return true
    }


}