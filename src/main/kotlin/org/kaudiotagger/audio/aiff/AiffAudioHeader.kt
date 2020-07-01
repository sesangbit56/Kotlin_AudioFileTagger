package org.kaudiotagger.audio.aiff

import org.kaudiotagger.audio.generic.GenericAudioHeader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder


//TODO 1차 작업 끝.
class AiffAudioHeader(private var fileType : AiffType? = null,
                      private var timestamp : Date? = null,
                      private var endian : Endian? = null,
                      private var audioEncoding : String? = null,
                      private var name : String? = null,
                      private var author : String? = null,
                      private var copyright : String? = null,

                      private var applicationIdentifiers : MutableList<String>? = null,
                      private var comments : MutableList<String>? = null,
                      private var annotations : MutableList<String>? = null) : GenericAudioHeader() {

    enum class Endian {
        BIG_ENDIAN,
        LITTLE_ENDIAN
    }


    constructor() {
        applicationIdentifiers = ArrayList()
        comments = ArrayList()
        annotations = ArrayList()
        endian = Endian.BIG_ENDIAN
    }

    fun getTimestamp() : Date? = timestamp

    fun setTimestamp(d : Date?) {
        timestamp = d
    }

    fun getFileType() : AiffType? = fileType

    fun setFileType(typ : AiffType?) {
        fileType = typ
    }

    fun getAuthor() : String? = author

    fun setAuthor(a : String?) {
        author = a
    }

    fun getName() : String? = name

    fun setName(n : String?) {
        name = n
    }

    fun getCopyright() : String? = copyright

    fun setCopyright(c : String?) {
        copyright = c
    }

    fun getEndian() : Endian? = endian

    fun setEndian(e : Endian?) {
        endian = e
    }

    fun getApplicationIdentifiers() : MutableList<String>? = applicationIdentifiers

    fun addApplicationIdentifiers(id : String?) {
        applicationIdentifiers?.add(id!!)
    }

    fun getAnnotations() : MutableList<String>? = annotations

    fun addAnnotations(a : String?) {
        annotations?.add(a!!)
    }

    fun getComments() : MutableList<String>? = comments

    fun addComments(c : String?) {
        comments?.add(c!!)
    }

    override fun toString(): String {
        val sb : StringBuilder = StringBuilder("\n")

        if(name != null && !name?.isEmpty()!!) sb.append("\tName:$name\n")
        if(author != null && !author?.isEmpty()!!) sb.append("\tAuthor:$author\n")
        if(copyright != null && !copyright?.isEmpty()!!) sb.append("\tCopyright:$copyright\n")
        if(comments?.size!! > 0) {
            sb.append("\tcomments:\n")
            for(next : String in comments!!) sb.append("\t$next\n")
        }
        if(applicationIdentifiers?.size!! > 0) {
            sb.append("Comments:\n")
            for(next : String in applicationIdentifiers!!) sb.append("\t$next\n")
        }
        if(annotations?.size!! > 0) {
            sb.append("Annotations:\n")
            for(next : String in annotations!!) sb.append("\t$next\n")
        }

        return super.toString() + sb.toString()
    }
}