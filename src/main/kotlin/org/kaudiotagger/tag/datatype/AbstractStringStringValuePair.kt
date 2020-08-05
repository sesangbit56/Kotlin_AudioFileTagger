package org.kaudiotagger.tag.datatype

import java.util.*

abstract class AbstractStringStringValuePair : AbstractValuePair<String, String>() {

    protected var lkey : String? = null

    fun getIdForValue(value : String) : String = valueToId.get(value).toString()

    fun getValueForId(id : String) : String = idToValue.get(id).toString()

    protected fun createMaps() {
        super.iterator = idToValue.keys.iterator()
        while (iterator.hasNext()) {
            lkey = iterator.next()
            value = idToValue.get(lkey!!)!!
            valueToId.put(value, lkey!!)
        }

        iterator = idToValue.keys.iterator()
        while (iterator.hasNext()) {
            valueList.add(idToValue.get(iterator.next())!!)
        }

        Collections.sort(valueList)
    }
}