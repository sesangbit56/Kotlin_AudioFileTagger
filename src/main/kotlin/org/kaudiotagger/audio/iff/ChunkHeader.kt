package org.kaudiotagger.audio.iff

import org.kaudiotagger.audio.generic.Utils
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.nio.charset.StandardCharsets

class ChunkHeader(byteOrder: ByteOrder) {

    companion object {
        val CHUNK_HEADER_SIZE : Int = 8
    }
    private var size : Long? = null
    private var chunkId : String? = null
    private var byteOrder : ByteOrder? = null
    private var startLocationInFile : Long? = null


    fun readHeader(fc : FileChannel) : Boolean {
        val header : ByteBuffer = ByteBuffer.allocate(CHUNK_HEADER_SIZE)
        startLocationInFile = fc.position()
        fc.read(header)
        header.order(byteOrder)
        header.position(0)
        chunkId = Utils.readFourBytesAsChars(header)
        //TODO int -> long 이런 2번의 변환과정이 꼭 필요한가> 확인 바람.
        size = header.getInt().toLong()

        return true
    }

    fun readHeader(raf : RandomAccessFile) : Boolean {
        val header : ByteBuffer = ByteBuffer.allocate(CHUNK_HEADER_SIZE)
        startLocationInFile = raf.filePointer
        raf.channel.read(header)
        header.order(byteOrder!!)
        header.position(0)
        chunkId = Utils.readFourBytesAsChars(header)
        size = header.getInt().toLong()

        return true
    }

    fun writeHeader() : ByteBuffer {
        val bb : ByteBuffer = ByteBuffer.allocate(CHUNK_HEADER_SIZE)
        bb.order(byteOrder!!)
        bb.put(chunkId?.toByteArray(StandardCharsets.US_ASCII))
        bb.putInt(size?.toInt()!!)
        bb.flip()
        return bb
    }

    fun setID(id : String) {
        chunkId = id
    }

    fun getID() : String? = chunkId

    fun getSize() : Long? = size

    fun setSize(size : Long?) {
        this.size = size
    }

    fun getStartLocationInFile() : Long? = startLocationInFile

    override fun toString(): String = "${getID()}:Size:${getSize()}:startLocation:${getStartLocationInFile()}"
}