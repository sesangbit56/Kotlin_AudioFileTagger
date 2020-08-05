package org.kaudiotagger.tag.id3

import java.io.RandomAccessFile
import java.nio.ByteBuffer

abstract class AbstractTag(copyObject: AbstractTag) : AbstractTagItem(copyObject) {

    companion object {
        protected val TYPE_TAG: String = "tag"
    }

    constructor()

    abstract fun seek(byteBuffer : ByteBuffer) : Boolean

    abstract fun write(file : RandomAccessFile)

    abstract fun delete(file : RandomAccessFile)

    override fun equals(obj : Any?) : Boolean {
        return (obj is AbstractTag) && super.equals(obj)
    }

    abstract fun iterator() : Iterator<Any?>

}