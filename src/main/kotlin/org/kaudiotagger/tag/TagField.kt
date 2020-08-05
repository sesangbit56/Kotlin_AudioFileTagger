package org.kaudiotagger.tag

interface TagField {

    fun copyContent(field : TagField)

    fun getId() : String?

    fun getRawContent() : ByteArray?

    fun isBinary() : Boolean

    fun isBinary(b : Boolean)

    fun isCommon() : Boolean

    fun isEmpty() : Boolean

    override fun toString() : String

}