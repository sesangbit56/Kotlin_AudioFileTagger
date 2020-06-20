package org.kaudiotagger

import org.kaudiotagger.audio.SupportedFileFormat

fun main(args : Array<String>) {
    println(SupportedFileFormat.MP3.getFilesuffix())
}