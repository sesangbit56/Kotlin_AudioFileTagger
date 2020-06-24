package org.kaudiotagger.audio.generic

import TagIterator
import org.kaudiotagger.tag.FieldKey
import org.kaudiotagger.tag.Tag
import org.kaudiotagger.tag.TagField
import org.kaudiotagger.tag.TagTextField
import org.kaudiotagger.tag.images.Artwork
import java.nio.charset.Charset

//TODO 배열들의 Mutable 여부 확인하고 때에 맞게 수정할 것
//TODO var & val 필요 확인하기
abstract class AbstractTag : Tag {

    protected var commonNumber : Int = 0

    protected var fields : MutableMap<String, MutableList<TagField>> = LinkedHashMap()

    override fun addField(field: TagField) {
        if(field == null) {
            return
        }
        var list : MutableList<TagField>? = fields.get(field.getId())

        if (list == null) {
            list = ArrayList()
            list.add(field)
            fields.put(field.getId(), list)
            if (field.isCommon()) {
                commonNumber++
            }
        } else {
            list.add(field)
        }
    }

    override fun getFields(id: String): MutableList<TagField> {
        var list : MutableList<TagField>? = fields.get(id)

        if (list == null) {
            return ArrayList()
        }

        return list
    }

    fun getAll(id : String) : List<String> {
        var fields : MutableList<String> = ArrayList()
        var tagFields : List<TagField> = getFields(id)
        for(tagField in tagFields) {
            fields.add(tagField.toString())
        }
        return fields
    }

    fun getItem(id : String, index : Int) : String {
        var l : MutableList<TagField> = getFields(id)
        return if (l.size > index) l.get(index).toString() else ""
    }

    override fun getFirst(genericKey : FieldKey) : String {
        return getValue(genericKey, 0)
    }

    override fun getFirst(id: String): String {
        val l : List<TagField> = getFields(id)
        return if (l.size != 0) l.get(0).toString() else ""
    }

    override fun getFirstField(id: String): TagField? {
        var l : List<TagField>? = getFields(id)
        return if (l?.size != 0) l?.get(0) else null
    }

    fun getAll() : MutableList<TagField> {
        val fieldList : MutableList<TagField> = ArrayList()
        for (listOfFields : List<TagField> in fields.values) {
            for(next : TagField in listOfFields) {
                fieldList.add(next)
            }
        }
        return fieldList
    }

    override fun getFields(): TagIterator {
        val it : MutableIterator<Map.Entry<String, List<TagField>>> = this.fields.entries.iterator()

        //로컬 함수의 선언 불가로 인해 따로 TagIterator를 사용하여 Iterator를 상속.
        return TagIterator(it)
    }

    override fun getFieldCount(): Int {
        val it : TagIterator = getFields()
        var count = 0
        while(it.hasNext()) {
            count++
            it.next()
        }
        return count
    }

    override fun getFieldCountIncludeingSubValues(): Int {
        return getFieldCount()
    }

    override fun hasCommonFields(): Boolean {
        return commonNumber != 0
    }

    override fun hasField(id: String) : Boolean {
        return getFields(id).size != 0
    }

    override fun hasField(fieldKey: FieldKey): Boolean {
        return hasField(fieldKey.name)
    }

    abstract fun isAllowedEncoding(enc : Charset) : Boolean

    //TODO 이 구현부를 isEmpty()로 바꿔도 되는가?
    override fun isEmpty(): Boolean {
        return fields.size == 0
    }

    override fun setField(genericKey: FieldKey, vararg value: String) {
        val tagfield : TagField = createField(genericKey, *value)
        setField(tagfield)
    }

    override fun addField(genericKey: FieldKey, vararg value: String) {
        val tagfield : TagField = createField(genericKey, *value)
        addField(tagfield)
    }

    override fun setField(field: TagField) {
        if(field == null) {
            return
        }

        var list : MutableList<TagField>? = fields.get(field.getId())
        if (list != null) {
            list.set(0, field)
            return
        }

        list = ArrayList()
        list.add(field)
        fields.put(field.getId(), list)
        if(field.isCommon()) {
            commonNumber++
        }
    }

    //TODO 모든 형변환을 없앴으나 안정성이 확보되지 못함. 추후 확인 바람.
    override fun setEncoding(enc: Charset): Boolean {
        if(isAllowedEncoding(enc)) {
            return false
        }

        var it = getFields()
        while(it.hasNext()) {
            val field : TagField = it.next()
            if(field is TagTextField) {
                field.setEncoding(enc)
            }
        }

        return true
    }

    override fun toString(): String {
        var out : StringBuffer = StringBuffer()
        out.append("Tag content:\n")
        val it : TagIterator = getFields()
        while (it.hasNext()) {
            val field : TagField = it.next()
            out.append("\t")
            out.append(field.getId())
            out.append(":")
            out.append(field.toString())
            out.append("\n")
        }
        return out.toString().substring(0, out.length - 1)
    }

    abstract override fun createField(genericKey: FieldKey, vararg value: String): TagField

    abstract override fun getFirstField(id: FieldKey): TagField

    abstract override fun deleteField(fieldKey: FieldKey)

    override fun deleteField(key: String) {
        fields.remove(key)
    }

    override fun getFirstArtwork(): Artwork? {
        var artwork : List<Artwork>? = getArtworkList()
        if(artwork?.size!! > 0) {
            return artwork.get(0)
        }
        return null
    }

    override fun setField(artwork: Artwork) {
        this.setField(createField(artwork))
    }

    override fun addField(artwork: Artwork) {
        this.addField(createField(artwork))
    }

    override fun deleteArtworkField() {
        this.deleteField(FieldKey.COVER_ART)
    }

}
