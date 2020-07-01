package org.kaudiotagger.audio.aiff.chunk

//TODO 최적화 필요
enum class AiffChunkType(var code : String) {
    FORMAT_VERSION("FVER"),
    APPLICATION("APPL"),
    SOUND("SSND"),
    COMMON("COMM"),
    COMENTS("COMT"),
    NAME("NAME"),
    AUTHOR("AUTH"),
    COPYRIGHT("(c) "),
    ANNOTATION("ANNO"),
    TAG("ID3 "),
    CORRUPT_TAG_LATE("D3 \u0000"),
    CORRUPT_TAG_EARLY("\u0000ID3");

    companion object {
        val CODE_TYPE_MAP : MutableMap<String, AiffChunkType> = HashMap()

        @Synchronized
        fun get(code : String) : AiffChunkType {
            if(CODE_TYPE_MAP.isEmpty()) {
                for(type : AiffChunkType in values()) CODE_TYPE_MAP.put(type.code, type)
            }
            return CODE_TYPE_MAP.get(code)!!
        }
    }


}