package org.kaudiotagger.tag.wav

import org.kaudiotagger.audio.generic.GenericTag
import org.kaudiotagger.audio.iff.ChunkHeader
import org.kaudiotagger.logging.Hex
import org.kaudiotagger.tag.FieldKey
import org.kaudiotagger.tag.TagField
import org.kaudiotagger.tag.TagTextField
import org.kaudiotagger.tag.images.Artwork
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class WavInfoTag : GenericTag(){

    private var unrecognisedFields : MutableList<TagTextField> = ArrayList()

    private var startLocationInFile : Long? = null

    private var endLocationInFile : Long? = null

    companion object {
        init {
            supportedKeys = EnumSet.of(
                    FieldKey.ALBUM,
                    FieldKey.ARTIST,
                    FieldKey.ALBUM_ARTIST,
                    FieldKey.TITLE,
                    FieldKey.TRACK,
                    FieldKey.GENRE,
                    FieldKey.COMMENT,
                    FieldKey.YEAR,
                    FieldKey.RECORD_LABEL,
                    FieldKey.ISRC,
                    FieldKey.COMPOSER,
                    FieldKey.LYRICIST,
                    FieldKey.ENCODER,
                    FieldKey.CONDUCTOR,
                    FieldKey.RATING)
        }
    }

    override fun toString(): String {
        val output : StringBuilder = StringBuilder("Wav Info Tag:\n")
        if(startLocationInFile != null) {
            output.append("\tstartLocation:${Hex.asDecAndHex(getStartLocationInFile()!!)}\n")
        }
        if(getEndLocationInFile() != null) {
            output.append("\tendLocation:${Hex.asDecAndHex(getEndLocationInFile()!!)}\n")
        }
        output.append(super.toString())
        if(unrecognisedFields.size > 0) {
            output.append("\nUnrecognized Tags:\n")
            for(next : TagTextField in unrecognisedFields) {
                output.append("\t${next.getId()}:${next.getContent()}\n")
            }
        }
        return output.toString()
    }


    override fun createCompilationField(value: Boolean): TagField = createField(FieldKey.IS_COMPILATION, value.toString())

    fun getStartLocationInFile() = startLocationInFile

    fun setStartLocationInFile(startLocationInFile : Long?) {
        this.startLocationInFile = startLocationInFile
    }

    fun getEndLocationInFile() : Long? = endLocationInFile

    fun setEndLocationInFile(endLocationInFile : Long?) = endLocationInFile

    fun getSizeOfTag() : Long = if(endLocationInFile == null || startLocationInFile == null) 0 else endLocationInFile!! - startLocationInFile!! - ChunkHeader.CHUNK_HEADER_SIZE

    fun addUnRecognizedField(code : String, contents : String) {
        unrecognisedFields.add(GenericTagTextField(code, contents))
    }

    fun getUnrecogzisedFields() : MutableList<TagTextField> = unrecognisedFields
}