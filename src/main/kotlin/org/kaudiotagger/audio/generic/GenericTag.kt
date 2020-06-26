package org.kaudiotagger.audio.generic

import org.kaudiotagger.logging.ErrorMessage
import org.kaudiotagger.tag.FieldKey
import org.kaudiotagger.tag.TagField
import org.kaudiotagger.tag.TagTextField
import java.lang.IllegalArgumentException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.ArrayList


//TODO nullable 타입 변수 선언에 심각한 오류가 있을 것으로 예상됨 추후 수정 바람.
abstract class GenericTag : AbstractTag {
    companion object {
        val EMPTY_BYTE_ARRAY : ByteArray? = null
        protected var supportedKeys : EnumSet<FieldKey> = EnumSet.of(FieldKey.ALBUM, FieldKey.ARTIST, FieldKey.TITLE, FieldKey.TRACK, FieldKey.GENRE, FieldKey.COMMENT, FieldKey.YEAR)
    }

    protected class GenericTagTextField(private var content : String? = null, private val id : String? = null) : TagTextField {


        override fun copyContent(field: TagField) {
            if(field is TagTextField) {
                //TODO 형변환 생략. 확인 바람
                content = field.getContent()
            }
        }

        override fun getContent(): String? = content

        override fun getEncoding() : Charset? = StandardCharsets.ISO_8859_1

        override fun getId(): String? = id

        override fun getRawContent(): ByteArray? = if(content == null) EMPTY_BYTE_ARRAY else content!!.toByteArray(getEncoding() as Charset)

        override fun isBinary(): Boolean = false

        override fun isBinary(b: Boolean) {
            //지원되지 않음
        }

        override fun isCommon(): Boolean = true

        override fun isEmpty(): Boolean = "".equals(content)

        override fun setContent(content: String?) {
            this.content = content
        }

        override fun setEncoding(encoding: Charset?) {
            //허용되지 않는 작업
        }

        override fun toString(): String = getContent()!!

    }

    override fun isAllowedEncoding(enc: Charset): Boolean = true

    override fun createField(genericKey: FieldKey, vararg value: String): TagField {
        if(supportedKeys.contains((genericKey))) {
            if(value == null || value[0] == null) {
                throw IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg())
            }
            return GenericTagTextField(genericKey.name, value[0])
        } else {
            throw UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(genericKey))
        }

    }

    override fun getFirst(genericKey: FieldKey): String = getValue(genericKey, 0)

    override fun getValue(id: FieldKey, n: Int): String {
        if(supportedKeys.contains(id)) {
            return getItem(id.name, n)
        } else {
            throw UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(id))
        }
    }

    override fun getFields(id: FieldKey): List<TagField> {
        val list : MutableList<TagField>? = fields?.get(id.name)
        if(list == null) {
            return ArrayList<TagField>()
        }
        return list
    }

    override fun getAll(id: FieldKey): List<String> {
        return super.getAll(id.name)
    }

    override fun deleteField(fieldKey: FieldKey) {
        if(supportedKeys.contains(fieldKey)) {
            deleteField(fieldKey.name)
        } else {
            throw UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(fieldKey))
        }
    }

    override fun getFirstField(id: FieldKey): TagField {
        if(supportedKeys.contains(id)) {
            return getFirstField(id.name)!!
        } else {
            throw UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg())
        }
    }
}