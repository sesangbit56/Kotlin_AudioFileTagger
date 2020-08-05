package org.kaudiotagger.tag.id3

interface Id3SupportingTag {
    fun getID3Tag() : AbstractID3v2Tag

    fun setID3Tag(t : AbstractId3v2Tag)
}