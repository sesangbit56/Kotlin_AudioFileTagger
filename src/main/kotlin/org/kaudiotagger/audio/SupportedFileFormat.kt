package org.kaudiotagger.audio

enum class SupportedFileFormat(val extension : String) {
    OGG("ogg"),
    MP3("mp3"),
    FLAC("flac"),
    MP4("mp4"),
    M4A("m4a"),
    M4P("m4p"),
    WMA("wma"),
    WAV("wav"),
    RA("ra"),
    RM("rm"),
    M4B("m4b"),
    AIF("aif"),
    AIFF("aiff"),
    AIFC("aifc"),
    DSF("dsf");

    fun getFilesuffix() : String = extension

}