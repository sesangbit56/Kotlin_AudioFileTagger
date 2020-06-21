package org.kaudiotagger.audio

import java.io.File
import java.io.FileFilter
import java.lang.IllegalArgumentException

class AudioFileFilter(val allowDirectories : Boolean = true) : FileFilter {

    override fun accept(f: File?): Boolean {
        TODO("아래의 canRead()의 안정성이 확보되지 않음")
        if(f?.isHidden()!! || !f?.canRead()) return false

        if(f.isDirectory) return allowDirectories

        val ext : String = Utils.getExtension(f)

        try {
            if(SupportedFileFormat.valueOf(ext.toUpperCase()) != null) return true
        } catch(iae : IllegalArgumentException) {
            return false
        }

        return false
    }

}