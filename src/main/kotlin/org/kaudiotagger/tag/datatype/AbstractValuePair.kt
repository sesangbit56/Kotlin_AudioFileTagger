package org.kaudiotagger.tag.datatype

abstract class AbstractValuePair<I, V> {
    protected val idToValue : MutableMap<I, V> = LinkedHashMap()
    protected val valueToId : MutableMap<V, I> = LinkedHashMap()
    protected val valueList : MutableList<V> = ArrayList()

    protected var iterator : Iterator<I> = idToValue.keys.iterator()

    protected abstract var value : String

    fun getAlphabeticalValueList() : MutableList<V> = valueList

    fun getIdToValueMap() : MutableMap<I, V> = idToValue

    fun getValueToIdMap() : MutableMap<V, I> = valueToId

    fun getSize() : Int = valueList.size
}