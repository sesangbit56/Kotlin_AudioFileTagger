package org.kaudiotagger.audio

interface AudioHeader {

    var encodingType : String?

    var byteRate : Int?

    var bitRateAsNumber : Long?

    var audioDataLength : Long?

    var audioDataStartPosition : Long?

    var audioDataEndPosition : Long?

    var sampleRateAsNumber : Int?

    var sampleRate : String?

    var format : String?

    var channels : String?

    var variableBitRate : Boolean

    var trackLength : Int?

    var preciseTrackLength : Double?

    var bitsPerSample : Int?

    var isLossless : Boolean?

    var getNoOfSamples : Long?


}