package org.kaudiotagger.audio.iff

import org.kaudiotagger.logging.Hex

class ChunkSummary(private var chunkId : String? = null,
                   private var fileStartLocation : Long? = null,
                   private var chunkSize : Long? = null) {

    override fun toString(): String {
        val endLocation : Long = fileStartLocation!! + chunkSize!! + ChunkHeader.CHUNK_HEADER_SIZE
        return "$chunkId:StartLocation:${Hex.asDecAndHex(fileStartLocation!!)}:SizeIncHeader:$chunkSize${ChunkHeader.CHUNK_HEADER_SIZE}:EndLocation:${Hex.asDecAndHex(endLocation)}"
    }

    fun getEndLocation(): Long {
        return fileStartLocation!! + chunkSize!! + ChunkHeader.CHUNK_HEADER_SIZE
    }

    fun getChunkId(): String? {
        return chunkId
    }

    fun setChunkId(chunkId: String?) {
        this.chunkId = chunkId
    }

    fun getFileStartLocation(): Long {
        return fileStartLocation!!
    }

    fun setFileStartLocation(fileStartLocation: Long) {
        this.fileStartLocation = fileStartLocation
    }

    fun getChunkSize(): Long {
        return chunkSize!!
    }

    fun setChunkSize(chunkSize: Long) {
        this.chunkSize = chunkSize
    }
}