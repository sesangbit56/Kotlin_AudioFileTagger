package org.kaudiotagger.audio.aiff

import org.kaudiotagger.audio.generic.AudioFileReader2
import org.kaudiotagger.audio.generic.GenericAudioHeader
import org.kaudiotagger.tag.Tag
import java.nio.file.Path

class AiffFileReader : AudioFileReader2() {
    private val ir : AiffInfoReader = AiffInfoReader()
    private val im : AiffTagReader = AiffTagReader()

    override fun getEncodingInfo(path: Path): GenericAudioHeader {
        return ir.read(path)
    }

    override fun getTag(path: Path): Tag {
        return im.read(path)
    }

}