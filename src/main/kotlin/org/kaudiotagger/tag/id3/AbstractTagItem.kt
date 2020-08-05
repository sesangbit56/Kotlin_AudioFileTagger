package org.kaudiotagger.tag.id3

import java.nio.ByteBuffer
import java.util.*
import java.util.logging.Logger

abstract class AbstractTagItem() {

    companion object {
        val logger : Logger = Logger.getLogger("org.jaudiotagger.tag.id3")
    }

    constructor(copyObject : AbstractTagItem)

    abstract fun getIdentifier() : String

    abstract fun getSize() : Int

    abstract fun read(byteBuffer: ByteBuffer)

    fun isSubsetOf(obj : Any?) = obj is AbstractTagItem

    override fun equals(obj : Any?): Boolean = if (this === obj) true else obj is AbstractTagItem


}