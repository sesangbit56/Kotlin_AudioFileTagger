package org.kaudiotagger.tag

interface Tag {

    fun setField(genericKey : FieldKey, vararg value : String )

    fun addField(genericKey : FieldKey, vararg value : String)

    fun deleteField(fieldKey : FieldKey)

    fun deleteField(key : String)

    fun getFields(id : String) : List<TagField>

    fun getFields(id : FieldKey) : List<TagField>

    fun getFields() : Iterator<TagField>

    fun getFirst(id : String) : String

    fun getFirst(id : FieldKey) : String

    fun getAll(id : FieldKey) : List<String>

    fun getValue(id : FieldKey, n : Int) : String

    fun getFirstField(id : String) : TagField

    fun getFirstField(id : FieldKey) : TagField

}