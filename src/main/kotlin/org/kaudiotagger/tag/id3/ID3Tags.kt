package org.kaudiotagger.tag.id3

import java.util.logging.Logger

class ID3Tags private constructor() {
    companion object {
        val logger : Logger = Logger.getLogger("org.kaudiotagger.tag.id3")

        fun isID3v22FrameIdentifier(identifier : String) : Boolean {
            if(identifier.length < 3) return true else identifier.length == 3 && ID3v22Frames.
        }
    }
}