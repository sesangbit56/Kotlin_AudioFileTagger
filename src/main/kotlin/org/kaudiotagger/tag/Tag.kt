package org.kaudiotagger.tag

import TagIterator
import org.kaudiotagger.tag.images.Artwork
import java.nio.charset.Charset

//TODO Mutable 여부 확인
interface Tag {

    fun setField(genericKey : FieldKey, vararg value : String )

    fun addField(genericKey : FieldKey, vararg value : String)

    fun deleteField(fieldKey : FieldKey)

    fun deleteField(key : String)

    fun getFields(id : String) : List<TagField>

    fun getFields(id : FieldKey) : List<TagField>

    fun getFields() : TagIterator

    fun getFirst(id : String) : String

    fun getFirst(id : FieldKey) : String

    fun getAll(id : FieldKey) : List<String>

    fun getValue(id : FieldKey, n : Int) : String

    fun getFirstField(id : String) : TagField?

    fun getFirstField(id : FieldKey) : TagField

    fun hasCommonFields() : Boolean

    fun hasField(fieldKey: FieldKey) : Boolean

    fun hasField(id : String) : Boolean

    fun isEmpty() : Boolean

    override fun toString() : String

    fun getFieldCount() : Int

    fun getFieldCountIncludeingSubValues() : Int

    fun setEncoding(enc : Charset) : Boolean

    fun getArtworkList() : List<Artwork>

    fun getFirstArtwork() : Artwork?

    fun deleteArtworkField()

    fun createField(artwork : Artwork) : TagField

    fun setField(artwork : Artwork)

    fun addField(artwork : Artwork)

    fun setField(field : TagField)

    fun addField(field : TagField)

    fun createField(genericKey: FieldKey, vararg value : String) : TagField

    fun createCompilationField(value : Boolean) : TagField

}