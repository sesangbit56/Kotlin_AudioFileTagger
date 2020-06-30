package org.kaudiotagger.logging

class Hex {

    companion object {
        fun asHex(temp : Long) : String {
            val value : String = java.lang.Long.toHexString(temp)
            if(value.length == 1) {
                return "0x0$value"
            }
            return "0x$value"
        }

        fun asHex(value : Int) : String = "0x${Integer.toHexString(value)}"

        fun asHex(value : Byte) : String = "0x${Integer.toHexString(value.toInt())}"

        fun asDecAndHex(value : Long) : String = "$value (${Hex.asHex(value)})"
    }
}