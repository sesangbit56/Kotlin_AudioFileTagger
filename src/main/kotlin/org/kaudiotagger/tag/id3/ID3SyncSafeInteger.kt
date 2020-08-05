package org.kaudiotagger.tag.id3

import java.nio.ByteBuffer
import kotlin.experimental.and

class ID3SyncSafeInteger {
    companion object {
        val INTEGRAL_SIZE: Int = 4
        val MAX_SAFE_SIZE: Int = 127

        fun bufferToValue(buffer : ByteArray) : Int {
            return (((buffer[0].toInt() and 0xff) shl 21) + ((buffer[1].toInt() and 0xff) shl 14) + ((buffer[2].toInt() and 0xff) shl 7) + (buffer[3].toInt() and 0xff))
        }

        fun bufferToValue(buffer : ByteBuffer) : Int {
            val byteBuffer : ByteArray = ByteArray(INTEGRAL_SIZE)
            buffer.get(byteBuffer, 0, INTEGRAL_SIZE)
            return bufferToValue(byteBuffer)
        }

        fun isBufferNotSyncSafe(buffer : ByteBuffer) : Boolean {
            val position : Int = buffer.position()

            for(i in 0 until INTEGRAL_SIZE) {
                val nextByte : Byte = buffer.get(position + i)
                if((nextByte.toInt() and 0x80) > 0) {
                    return true
                }
            }
            return false
        }

        protected fun valueToBuffer(size : Int) : ByteArray {
            val buffer : ByteArray = ByteArray(4)
            buffer[0] = ((size and 0x0FE00000) shr 21).toByte()
            buffer[1] = ((size and 0x001FC000) shr 14).toByte()
            buffer[2] = ((size and 0x00003F80) shr 7).toByte()
            buffer[3] = (size and 0x0FE0007F).toByte()
            return buffer
        }
    }
}