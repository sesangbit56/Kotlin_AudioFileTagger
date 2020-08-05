package org.kaudiotagger.tag.id3

import org.kaudiotagger.audio.generic.Utils
import org.kaudiotagger.tag.Tag
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.util.*
import kotlin.collections.LinkedHashMap

abstract class AbstractID3v2Tag() : AbstractID3Tag, Tag {

    companion object {
        protected val TYPE_HEADER : String = "header"
        protected val TYPE_BODY : String = "body"

        val TAG_ID : ByteArray = byteArrayOf('I'.toByte(), 'D'.toByte(), '3'.toByte())
        val TAGID : String = "ID3"

        val TAG_HEADER_LENGTH : Int = 10
        val FIELD_TAGID_LENGTH : Int = 3
        val FIELD_TAG_MAJOR_VERSION_LENGTH : Int = 1
        val FIELD_TAG_MINOR_VERSION_LENGTH : Int = 1
        val FIELD_TAG_FLAG_LENGTH : Int = 1
        val FIELD_TAG_SIZE_LENGTH : Int = 4

        protected val FIELD_TAGID_POS : Int = 0
        val FIELD_TAG_MAJOR_VERSION_POS : Int = 3
        protected val FIELD_TAG_MINOR_VERSION_POS : Int = 4
        protected val FIELD_TAG_FLAG_POS : Int = 5
        protected val FIELD_TAG_SIZE_POS : Int = 6

        protected val TAG_SIZE_INCREMENT : Int = 100

        private val MAXIMUM_WRITABLE_CHUNK_SIZE : Long = 10000000


        protected val TYPE_DUPLICATEFRAMEID : String = "duplicateFrameId"

        protected val TYPE_DUPLICATEBYTES : String = "duplicateBytes"

        protected val TYPE_EMPTYFRAMEBYTES : String = "emptyFrameBytes"

        protected val TYPE_FILEREADSIZE : String = "fileReadSize"

        protected val TYPE_INVALIDFRAMES : String = "invalidFrames"

        private fun isID3V2Header(raf : RandomAccessFile) : Boolean {
            val start : Long = raf.filePointer
            val tagIdentifier : ByteArray = byteArrayOf(FIELD_TAGID_LENGTH.toByte())
            raf.read(tagIdentifier)
            raf.seek(start)
            if(!(Arrays.equals(tagIdentifier, TAG_ID))) return false
            return true
        }

        private fun isID3V2Header(fc : FileChannel) : Boolean {
            val start : Long = fc.position()
            val headerBuffer : ByteBuffer = Utils.readFileDataIntoBufferBE(fc, FIELD_TAGID_LENGTH)
            fc.position(start)
            val s : String = Utils.readThreeBytesAsChars(headerBuffer)
            return s.equals(TAGID)
        }

        fun isId3Tag(raf : RandomAccessFile) : Boolean {
            if(!isID3V2Header(raf)) {
                return false
            }

            val tagHeader : ByteArray = byteArrayOf(FIELD_TAG_SIZE_LENGTH.toByte())
            raf.seek(raf.filePointer + FIELD_TAGID_LENGTH + FIELD_TAG_MAJOR_VERSION_LENGTH + FIELD_TAG_MINOR_VERSION_LENGTH + FIELD_TAG_FLAG_LENGTH)
            raf.read(tagHeader)
            val bb : ByteBuffer = ByteBuffer.wrap(tagHeader)

            val size : Int = ID3SyncSafeInteger.bufferToValue(bb)
            raf.seek(size + TAG_HEADER_LENGTH.toLong())
            return true
        }

        fun isId3Tag(fc : FileChannel) : Boolean {
            if(!isID3V2Header(fc)) {
                return false
            }

            val bb : ByteBuffer = ByteBuffer.allocateDirect(FIELD_TAG_SIZE_LENGTH)
            fc.position(fc.position() + FIELD_TAGID_LENGTH + FIELD_TAG_MAJOR_VERSION_LENGTH + FIELD_TAG_MINOR_VERSION_LENGTH + FIELD_TAG_FLAG_LENGTH)
            fc.read(bb)
            bb.flip()
            val size : Int = ID3SyncSafeInteger.bufferToValue(bb)
            fc.position(size + TAG_HEADER_LENGTH.toLong())
            return true
        }
    }

    constructor(copyObject : AbstractID3v2Tag)

    private var startLocationInFile : Long? = null

    private var endLocationInFile : Long? = null

    var frameMap : MutableMap<String, Any>? = null

    var encryptedFrameMap : MutableMap<String, Any>? = null

    var duplicateFrameId : String = ""
    var duplicateBytes : Int = 0
    var emptyFrameBytes : Int = 0
    var fileReadSize : Int = 0
    var invalidFrames : Int = 0

    protected fun copyPrimitives(copyObject : AbstractID3v2Tag) {
        logger.config("Copying Primitives")
        this.duplicateFrameId = copyObject.duplicateFrameId
        this.duplicateBytes = copyObject.duplicateBytes
        this.emptyFrameBytes = copyObject.emptyFrameBytes
        this.fileReadSize = copyObject.fileReadSize
        this.invalidFrames = copyObject.invalidFrames
    }

    protected fun copyFrames(copyObject: AbstractID3v2Tag) {
        val frameMap = LinkedHashMap<String, Any>()
        val encryptedFrameMap = LinkedHashMap<String, Any>()

        for(o1 : Any in copyObject.frameMap?.keys!!) {
            val id : String = o1 as String
            val o : Any = copyObject.frameMap?.get(id)!!

            if(o is AbstractID3v2Tag) {
                addFrame(o as AbstractID3v2Frame)
            }
        }
    }



}