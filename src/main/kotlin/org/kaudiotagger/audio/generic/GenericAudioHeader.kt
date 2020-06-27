package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioHeader
import kotlin.math.roundToInt
import kotlin.text.StringBuilder

//TODO 변수 & 함수명을 바꾸는 등 좀 더 효율적인 방법이 있을 것임. 확인 바람.
//TODO 상당한 양의 오류가 있을 것으로 보임. 추후 확인 바람.
class GenericAudioHeader(private var audioDataLength : Long? = null,
                         private var audioDataStartPosition : Long? = null,
                         private var audioDataEndPosition : Long? = null,
                         private var bitRate : Int? = null,
                         private var noOfChannels : Int? = null,
                         private var samplingRate : Int? = null,
                         private var bitsPerSample : Int? = null,
                         private var encodingType : String? = null,
                         private var isVbr : Boolean? = null,
                         private var isLossless : Boolean? = null,
                         private var trackLength : Double? = null,
                         private var noOfSamples : Long? = null,
                         private var byteRate : Int? = null): AudioHeader {

    override fun getBitRate(): String? = bitRate as String?

    override fun getBitRateAsNumber() : Long? = bitRate as Long?

    fun getChannelNumber() : Int? = noOfChannels

    override fun getChannels(): String? = getChannelNumber() as String?

    override fun getEncodingType(): String? = encodingType

    override fun getFormat(): String? = encodingType

    override fun getTrackLength(): Int?  = getPreciseTrackLength()?.roundToInt()

    override fun getPreciseTrackLength(): Double? = trackLength

    override fun getSampleRate(): String? = samplingRate as String?

    override fun getSampleRateAsNumber(): Int? = samplingRate

    override fun getBitsPerSample(): Int? = if(bitsPerSample == null) -1 else bitsPerSample

    override fun isVariableBitRate(): Boolean? = if(isVbr == null) false else isVbr

    override fun isLossless(): Boolean? = if(isLossless == null) false else isLossless

    fun setBitRate(bitRate : Int?) {
        this.bitRate = bitRate
    }

    fun setChannelNumber(channelMode : Int?) {
        this.noOfChannels = channelMode
    }

    fun setEncodingType(encodingType : String?) {
        this.encodingType = encodingType
    }

    fun setPreciseLength(length : Double?) {
        this.trackLength = length
    }

    fun setSamplingRate(samplingRate : Int?) {
        this.samplingRate = samplingRate
    }

    fun setBitsPerSample(bitsPerSample : Int?) {
        this.byteRate = bitsPerSample
    }

    fun setByteRate(byteRate : Int?) {
        this.byteRate = byteRate
    }

    fun setVariableBitRate(isVbr : Boolean?) {
        this.isVbr = isVbr
    }

    fun setLossless(isLossless : Boolean) {
        this.isLossless = isLossless
    }

    override fun toString(): String {
        val out : StringBuilder = StringBuilder()
        out.append("Audio Header content:\n")
        if(audioDataLength != null) {
            out.append("\taudioDataLength:$audioDataLength\n")
        }
        if(audioDataStartPosition != null) {
            out.append("\taudioDataStartPosition:$audioDataStartPosition\n")
        }
        if(audioDataEndPosition != null) {
            out.append("\taudioDataEndPosition:$audioDataEndPosition\n")
        }
        if(byteRate != null) {
            out.append("\tbyteRate:$byteRate\n")
        }
        if(bitRate != null) {
            out.append("\tbitRate:$bitRate\n")
        }
        if(samplingRate != null) {
            out.append("\tsamplingRate:$samplingRate\n")
        }
        if(bitsPerSample != null) {
            out.append("\tbitsPerSample:$bitsPerSample\n")
        }
        if(noOfSamples != null) {
            out.append("\ttotalNoSamples:$noOfSamples\n")
        }
        if(noOfChannels != null) {
            out.append("\tnumberOfChannels:$noOfChannels\n")
        }
        if(encodingType != null) {
            out.append("\tencodingType:$encodingType\n")
        }
        if(isVbr != null) {
            out.append("\tisVbr:$isVbr\n")
        }
        if(isLossless != null) {
            out.append("\tisLossless:$isLossless\n")
        }
        if(trackLength != null) {
            out.append("\ttrackDuration:$trackLength\n")
        }
        return out.toString()
    }

    override fun getAudioDataLength() : Long? = audioDataLength

    fun setAudioDataLength(audioDataLength : Long?) {
        this.audioDataLength = audioDataLength
    }

    override fun getByteRate() : Int? = byteRate

    override fun getNoOfSamples(): Long? = noOfSamples

    fun setNoOfSamples(noOfSamples : Long?) {
        this.noOfSamples = noOfSamples
    }

    override fun getAudioDataStartPosition(): Long? = audioDataStartPosition

    fun setAudioDataStartPosition(audioDataStartPosition : Long?) {
        this.audioDataStartPosition = audioDataStartPosition
    }

    override fun getAudioDataEndPosition(): Long? = audioDataEndPosition

    fun setAudioDataEndPosition(audioDataEndPosition : Long?) {
        this.audioDataEndPosition = audioDataEndPosition
    }
}