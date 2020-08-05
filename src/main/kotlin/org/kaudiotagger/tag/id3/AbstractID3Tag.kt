package org.kaudiotagger.tag.id3

import org.kaudiotagger.audio.generic.AbstractTag
import java.util.logging.Logger

abstract class AbstractID3Tag : AbstractTag() {

    companion object {
        val logger : Logger = Logger.getLogger("org.kaudiotagger.tag.id3")

        val TAG_RELEASE : String = "ID3v"
    }

    override fun getIdentifier() : String = "${TAG_RELEASE}${getRelease()}.${getMajorVersion()}"

    override fun g
}