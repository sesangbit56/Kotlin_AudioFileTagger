package org.kaudiotagger.audio.generic

import org.kaudiotagger.tag.Tag
import java.nio.ByteBuffer

abstract class AbstractTagCreator {

    fun convert(tag : Tag) : ByteBuffer {
        return convert(tag, 0)
    }

    abstract fun convert(tag : Tag, padding : Int) : ByteBuffer
}