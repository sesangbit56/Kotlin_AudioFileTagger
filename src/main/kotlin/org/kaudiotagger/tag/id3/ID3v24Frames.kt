package org.kaudiotagger.tag.id3

class ID3v24Frames : ID3Frames() {
    companion object {
        val FRAME_ID_ACCOMPANIMENT : String = "TPE2"
        val FRAME_ID_ALBUM : String = "TALB"
        val FRAME_ID_ALBUM_SORT_ORDER : String = "TSOA"
        val FRAME_ID_ARTIST : String = "TPE1"
        val FRAME_ID_ATTACHED_PICTURE : String = "APIC"
        val FRAME_ID_AUDIO_ENCRYPTION : String = "AENC"
        val FRAME_ID_AUDIO_SEEK_POINT_INDEX : String = "ASPI"
        val FRAME_ID_BPM : String = "TBPM"
        val FRAME_ID_COMMENT : String = "COMM"
        val FRAME_ID_COMMERCIAL_FRAME : String = "COMR"
        val FRAME_ID_COMPOSER : String = "TCOM"
        val FRAME_ID_CONDUCTOR : String = "TPE3"
        val FRAME_ID_CONTENT_GROUP_DESC : String = "TIT1"
        val FRAME_ID_COPYRIGHTINFO : String = "TCOP"
        val FRAME_ID_ENCODEDBY : String = "TENC"
        val FRAME_ID_ENCODING_TIME : String = "TDEN"
        val FRAME_ID_ENCRYPTION : String = "ENCR"
        val FRAME_ID_EQUALISATION2 : String = "EQU2"
        val FRAME_ID_EVENT_TIMING_CODES : String = "ETCO"
        val FRAME_ID_FILE_OWNER : String = "TOWN"
        val FRAME_ID_FILE_TYPE : String = "TFLT"
        val FRAME_ID_GENERAL_ENCAPS_OBJECT : String = "GEOB"
        val FRAME_ID_GENRE : String = "TCON"
        val FRAME_ID_GROUP_ID_REG : String = "GRID"
        val FRAME_ID_HW_SW_SETTINGS : String = "TSSE"
        val FRAME_ID_INITIAL_KEY : String = "TKEY"
        val FRAME_ID_INVOLVED_PEOPLE : String = "TIPL"
        val FRAME_ID_ISRC : String = "TSRC"
        val FRAME_ID_ITUNES_GROUPING : String = "GRP1"
        val FRAME_ID_LANGUAGE : String = "TLAN"
        val FRAME_ID_LENGTH : String = "TLEN"
        val FRAME_ID_LINKED_INFO : String = "LINK"
        val FRAME_ID_LYRICIST : String = "TEXT"
        val FRAME_ID_MEDIA_TYPE : String = "TMED"
        val FRAME_ID_MOOD : String = "TMOO"
        val FRAME_ID_MOVEMENT : String = "MVNM"
        val FRAME_ID_MOVEMENT_NO : String = "MVIN"
        val FRAME_ID_MPEG_LOCATION_LOOKUP_TABLE : String = "MLLT"
        val FRAME_ID_MUSICIAN_CREDITS : String = "TMCL"
        val FRAME_ID_MUSIC_CD_ID : String = "MCDI"
        val FRAME_ID_ORIGARTIST : String = "TOPE"
        val FRAME_ID_ORIGINAL_RELEASE_TIME : String = "TDOR"
        val FRAME_ID_ORIG_FILENAME : String = "TOFN"
        val FRAME_ID_ORIG_LYRICIST : String = "TOLY"
        val FRAME_ID_ORIG_TITLE : String = "TOAL"
        val FRAME_ID_OWNERSHIP : String = "OWNE"
        val FRAME_ID_ARTIST_SORT_ORDER : String = "TSOP"
        val FRAME_ID_PLAYLIST_DELAY : String = "TDLY"
        val FRAME_ID_PLAY_COUNTER : String = "PCNT"
        val FRAME_ID_POPULARIMETER : String = "POPM"
        val FRAME_ID_POSITION_SYNC : String = "POSS"
        val FRAME_ID_PRIVATE : String = "PRIV"
        val FRAME_ID_PRODUCED_NOTICE : String = "TPRO"
        val FRAME_ID_PUBLISHER : String = "TPUB"
        val FRAME_ID_RADIO_NAME : String = "TRSN"
        val FRAME_ID_RADIO_OWNER : String = "TRSO"
        val FRAME_ID_RECOMMENDED_BUFFER_SIZE : String = "RBUF"
        val FRAME_ID_RELATIVE_VOLUME_ADJUSTMENT2 : String = "RVA2"
        val FRAME_ID_RELEASE_TIME : String = "TDRL"
        val FRAME_ID_REMIXED : String = "TPE4"
        val FRAME_ID_REVERB : String = "RVRB"
        val FRAME_ID_SEEK : String = "SEEK"
        val FRAME_ID_SET : String = "TPOS"
        val FRAME_ID_SET_SUBTITLE : String = "TSST"
        val FRAME_ID_SIGNATURE : String = "SIGN"
        val FRAME_ID_SYNC_LYRIC : String = "SYLT"
        val FRAME_ID_SYNC_TEMPO : String = "SYTC"
        val FRAME_ID_TAGGING_TIME : String = "TDTG"
        val FRAME_ID_TERMS_OF_USE : String = "USER"
        val FRAME_ID_TITLE : String = "TIT2"
        val FRAME_ID_TITLE_REFINEMENT : String = "TIT3"
        val FRAME_ID_TITLE_SORT_ORDER : String = "TSOT"
        val FRAME_ID_TRACK : String = "TRCK"
        val FRAME_ID_UNIQUE_FILE_ID : String = "UFID"
        val FRAME_ID_UNSYNC_LYRICS : String = "USLT"
        val FRAME_ID_URL_ARTIST_WEB : String = "WOAR"
        val FRAME_ID_URL_COMMERCIAL : String = "WCOM"
        val FRAME_ID_URL_COPYRIGHT : String = "WCOP"
        val FRAME_ID_URL_FILE_WEB : String = "WOAF"
        val FRAME_ID_URL_OFFICIAL_RADIO : String = "WORS"
        val FRAME_ID_URL_PAYMENT : String = "WPAY"
        val FRAME_ID_URL_PUBLISHERS : String = "WPUB"
        val FRAME_ID_URL_SOURCE_WEB : String = "WOAS"
        val FRAME_ID_USER_DEFINED_INFO : String = "TXXX"
        val FRAME_ID_USER_DEFINED_URL : String = "WXXX"
        val FRAME_ID_YEAR : String = "TDRC"

        val FRAME_ID_ALBUM_ARTIST_SORT_ORDER_ITUNES : String = "TSO2"
        val FRAME_ID_COMPOSER_SORT_ORDER_ITUNES : String = "TSOC"
        val FRAME_ID_IS_COMPILATION : String = "TCMP"

        val FRAME_ID_PERFORMER_SORT_OWNER : String = FRAME_ID_ARTIST_SORT_ORDER
        val FRAME_ID_TITLE_SORT_OWNER : String = FRAME_ID_TITLE_SORT_ORDER
    }
}