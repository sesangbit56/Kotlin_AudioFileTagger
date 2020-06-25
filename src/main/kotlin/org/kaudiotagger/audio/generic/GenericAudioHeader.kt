package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioHeader
import kotlin.math.roundToInt

//TODO 변수 & 함수명을 바꾸는 등 좀 더 효율적인 방법이 있을 것임. 확인 바람.
//TODO 상당한 양의 오류가 있을 것으로 보임. 추후 확인 바람.
//TODO 원본 형태가 더 나은가? 고민해볼 필요가 있을듯
class GenericAudioHeader: AudioHeader {
    override var bitRateAsNumber : Long? = null
    override var audioDataLength : Long? = null
    override var audioDataStartPosition : Long? = null
    override var audioDataEndPosition : Long? = null
    private var channelNumber : Int? = null
    override var sampleRateAsNumber : Int? = null
    override var bitsPerSample : Int? = null
        get() = if(field == null) -1 else field
    override var encodingType : String? = null
    private var vbr : Boolean? = true //TODO 객체를 생성하지 않았을 때 생기는 부작용이 있다면 수정할 것
    private var Lossless : Boolean? = null
    private var TrackLength : Int? = null
    override var noOfSamples : Long? = null
    override var byteRate : Int? = null


    private var bitRate = bitRateAsNumber as String?

    override var channels : String? = channelNumber as String?

    override var format : String? = this.encodingType

    override var preciseTrackLength : Double? = TrackLength as Double?
    //TODO track_Length가 non-null로 선언되었을지도 모름. 확인 바람.
    override var trackLength : Int? = preciseTrackLength?.roundToInt()

    override var sampleRate : String? = sampleRateAsNumber as String?

    override fun isVariableBitRate(): Boolean? = if(vbr == null) false else vbr

    override fun isLossless(): Boolean? = if(Lossless == null) false else Lossless

}