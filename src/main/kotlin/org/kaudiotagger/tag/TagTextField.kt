package org.kaudiotagger.tag

import java.nio.charset.Charset

interface TagTextField : TagField {

    fun getContent() : String

    fun getExcoding() : Charset

    fun setContent(content : String)

    fun setEncoding(encoding : Charset)
}