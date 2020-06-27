package org.kaudiotagger.audio

interface AudioHeader {

    fun getEncodingType() : String?

    fun getByteRate() : Int?

    fun getBitRate() : String?

    fun getBitRateAsNumber() : Long?

    fun getAudioDataLength() : Long?

    fun getAudioDataStartPosition() : Long?

    fun getAudioDataEndPosition() : Long?

    fun getSampleRate() : String?

    fun getSampleRateAsNumber() : Int?

    fun getFormat() : String?

    fun getChannels() : String?

    fun isVariableBitRate() : Boolean?

    fun getTrackLength() : Int?

    fun getPreciseTrackLength() : Double?

    fun getBitsPerSample() : Int?

    fun isLossless() : Boolean?

    fun getNoOfSamples() : Long?

}