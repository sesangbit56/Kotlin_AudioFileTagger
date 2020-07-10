package org.kaudiotagger.audio.aiff

import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*

//TODO 검토 바람

class AiffUtil {

    companion object{
        val dateFmt : SimpleDateFormat = SimpleDateFormat("yyyy=MM-dd'T'HH:mm:ss.SSSZ")

        fun read80BitDouble(chunkData : ByteBuffer) : Double? {
            val buf : ByteArray = ByteArray(10)
            chunkData.get(buf)
            val xd : ExtDouble = ExtDouble(buf)
            return xd.toDouble()
        }

        fun timestampToDate(timestamp : Long) : Date {
            var cal : Calendar = Calendar.getInstance()
            cal.set(1904, 0, 1, 0, 0, 0)

            var hours : Int = (timestamp/ 3600).toInt()
            var seconds : Int = (timestamp - hours.toLong() * 3600L).toInt()
            cal.add(Calendar.HOUR_OF_DAY, hours)
            cal.add(Calendar.SECOND, seconds)
            val dat : Date = cal.time
            return dat
        }

        fun formatDate(dat : Date) : String = dateFmt.format(dat)
    }
}