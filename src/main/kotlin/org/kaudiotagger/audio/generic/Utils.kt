package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioFile
import org.kaudiotagger.utils.FileTypeUtil
import java.io.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.ISO_8859_1
import java.nio.charset.StandardCharsets.US_ASCII
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.experimental.and

//TODO 오류 검사 필요.
class Utils {

    companion object {
        var BITS_IN_BYTE_MULTIPLIER : Int = 8
        var KILOBYTE_MULTIPLIER : Int = 1000

        val logger : Logger = Logger.getLogger("org.jaudiotagger.audio.generic.utils")
        const val MAX_BASE_TEMP_FILENAME_LENGTH : Int = 20

        fun getExtension(f : File) : String {
            val name : String = f.name.toLowerCase()
            val i : Int = name.lastIndexOf(".")
            if(i == -1) {
                return ""
            }

            return name.substring(i + 1)
        }

        fun getMagicExtension(f : File) : String? {
            val fileType : String? = FileTypeUtil.getMagicFileType(f)
            return FileTypeUtil.getMagicExt(fileType)
        }

        fun getLongLE(b : ByteBuffer?, start : Int, end : Int) : Long? {
            var number : Long = 0
            for (i in 0 until (start - end + 1)) {
                number += ((b?.get(start + i)!!.toInt() and 0xFF) shl (i * 8))
            }
            return number
        }

        fun getLongBE(b : ByteBuffer, start : Int, end : Int) : Long? {
            var number : Long = 0
            for(i in 0 until start - end + 1) {
                number += ((b.get(end - i).toInt() and 0xFF).toLong() shl i * 8)
            }

            return number
        }

        fun getIntLE(b : ByteArray) : Int = getLongLE(ByteBuffer.wrap(b), 0, b.size - 1)!!.toInt()

        fun getIntLE(b : ByteArray, start : Int, end : Int) : Int = getLongLE(ByteBuffer.wrap(b), start, end)!!.toInt()

        fun getIntBE(b : ByteBuffer, start : Int, end : Int) : Int = getLongBE(b, start, end)!!.toInt()

        fun getShortBE(b : ByteBuffer, start : Int, end : Int) : Short = getIntBE(b, start, end).toShort()

        fun getSizeBEInt32(size : Int) : ByteArray {
            val b : ByteArray = ByteArray(4)
            b[0] = ((size shr 24) and 0xFF).toByte()
            b[1] = ((size shr 16) and 0xFF).toByte()
            b[2] = ((size shr 8) and 0xFF).toByte()
            b[3] = (size and 0xFF).toByte()
            return b
        }

        fun getSizeLEInt32(size : Int) : ByteArray {
            val b : ByteArray = ByteArray(4)
            //TODO L사인을 지웠다. 이래도 문제가 없는지 확인 바람.
            b[0] = (size and 0xff).toByte()
            b[1] = (size ushr 8 and 0xff).toByte()
            b[2] = (size ushr 16 and 0xff).toByte()
            b[3] = (size ushr 24 and 0xff).toByte()
            return b
        }

        fun readPascalString(bb : ByteBuffer) : String {
            val len : Int = u(bb.get())
            val buf : ByteArray = ByteArray(len)
            bb.get(buf)
            return String(buf, 0, len, ISO_8859_1)
        }

        fun getString(buffer : ByteBuffer, offset : Int, length : Int, encoding : Charset) : String {
            val b : ByteArray = ByteArray(length)
            buffer.position(buffer.position() + offset)
            buffer.get(b)
            return String(b, 0, length, encoding)
        }

        fun readUint32(di : DataInput) : Long {
            val buf : ByteArray = byteArrayOf(0x00, 0x00, 0x00, 0x00)
            di.readFully(buf, 2, 2)
            //TODO 이렇게 get함수를 쓰지 않더라도 사용 가능한가? 확인 바람.
            return ByteBuffer.wrap(buf).long
        }

        fun readUint16(di : DataInput) : Int {
            val buf : ByteArray = byteArrayOf(0x00, 0x00, 0x00, 0x00)
            di.readFully(buf, 2, 2)
            //TODO 이렇게 get함수를 쓰지 않더라도 사용 가능한가? 확인 바람.
            return ByteBuffer.wrap(buf).int
        }

        fun readString(di : DataInput, charsToRead : Int) : String {
            val buf : ByteArray = byteArrayOf(charsToRead.toByte())
            di.readFully(buf)
            return String(buf, US_ASCII)
        }

        fun getBaseFilenameForTempFile(file : File) : String {
            val filename : String = getMinBaseFilenameAllowedForTempFile(file)
            if(filename.length <= MAX_BASE_TEMP_FILENAME_LENGTH) {
                return filename
            }
            return filename.substring(0, MAX_BASE_TEMP_FILENAME_LENGTH)
        }

        fun getMinBaseFilenameAllowedForTempFile(file : File) : String {
            val s : String = AudioFile.getBaseFilename(file)
            if(s.length >= 3) {
                return s
            }

            //TODO 이 조건문은 뭔가 확실히 잘못되었다. 최신 버전을 꼭 확인해보자.
            if(s.length == 1) {
                return s + "000"
            } else if (s.length == 1) {
                return s + "00"
            } else if (s.length == 2) {
                return s + "0"
            }
            return s
        }

        fun rename(fromFile : File , toFile : File) : Boolean {
            logger.log(Level.CONFIG, "Renaming From:${fromFile.absolutePath} to ${toFile.absolutePath}")

            if(toFile.exists()) {
                logger.log(Level.SEVERE, "Destination File:$toFile already exists")
                return false
            }

            val result : Boolean = fromFile.renameTo(toFile)
            if(!result){
                if(copy(fromFile, toFile)) {
                    val deleteResult : Boolean = fromFile.delete()
                    if(!deleteResult) {
                        logger.log(Level.SEVERE, "Unable to delete File:$fromFile")
                        toFile.delete()
                        return false
                    }
                    return true
                } else return false
            }
            return true
        }

        fun copy(fromFile : File, toFile: File) : Boolean {
            try {
                copyThrowsOnException(fromFile, toFile)
                return true
            } catch (e : IOException) {
                e.printStackTrace()
                return false
            }
        }

        fun readFourBytesAsChars(bytes : ByteBuffer) : String {
            val b : ByteArray = ByteArray(4)
            bytes.get(b)
            return String(b, ISO_8859_1)
        }

        fun readThreeBytesAsChars(bytes : ByteBuffer) : String {
            val b : ByteArray = ByteArray(3)
            bytes.get(b)
            return String(b, ISO_8859_1)
        }

        fun u(n : Int) : Long = (n and 0xffffffff.toInt()).toLong()

        fun u(n : Short) : Int = (n and 0xffff.toShort()).toInt()

        fun u(n : Byte) : Int = (n and 0xff.toByte()).toInt()

        fun readFileDataIntoBufferLE(fc : FileChannel, size : Int) : ByteBuffer {
            val tagBuffer : ByteBuffer = ByteBuffer.allocateDirect(size)
            fc.read(tagBuffer)
            tagBuffer.position(0)
            tagBuffer.order(ByteOrder.LITTLE_ENDIAN)
            return tagBuffer
        }

        fun readFileDataIntoBufferBE(fc : FileChannel, size : Int) : ByteBuffer {
            val tagBuffer : ByteBuffer = ByteBuffer.allocateDirect(size)
            fc.read(tagBuffer)
            tagBuffer.position(0)
            tagBuffer.order(ByteOrder.BIG_ENDIAN)
            return tagBuffer
        }
        //TODO What the Fuck? is that really work? >:(
        fun copyThrowsOnException(source : File, destination : File) {
            FileInputStream(source).use { inStream ->
                FileOutputStream(destination).use { outStream ->
                    val inChannel = inStream.channel
                    val outChannel = outStream.channel
                    val size = inChannel.size()
                    var position: Long = 0
                    while (position < size) {
                        position += inChannel.transferTo(position, 1024L * 1024L, outChannel)
                    }
                }
            }
        }

        //TODO 너무 비효율적. 방법을 찾아보자.
        fun isOddLength(length : Long) : Boolean = if((length and 1.toLong()) != 0.toLong()) true else false

    }
}