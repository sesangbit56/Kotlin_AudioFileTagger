package org.kaudiotagger.audio.aiff

//TODO 검토 필요

class ExtDouble(var _rawData : ByteArray? = null) {
    fun toDouble() : Double {
        var sign : Int
        var expenent : Int
        var mantissa : Long = 0

        sign = _rawData?.get(0)?.toInt()!! shl 8

        expenent = (_rawData?.get(0)?.toInt()!! shr  8) or _rawData?.get(1)?.toInt()!!
        expenent = expenent and 0X7FFF
        expenent -= (16383 + 62)

        var shifter : Int = 55
        for(i in 2 until 9) {
            mantissa = mantissa or ((_rawData?.get(i)?.toLong()!! and 0XFFL) shl shifter)
            shifter -= 8
        }

        mantissa = mantissa or (_rawData?.get(9)?.toInt()!! ushr 1).toLong()

        var bal : Double = Math.pow(2.0, expenent.toDouble())
        bal *= mantissa
        if(sign != 0) {
            bal = -bal
        }
        return bal
    }


}