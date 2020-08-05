package org.kaudiotagger.tag.id3

import org.kaudiotagger.tag.datatype.AbstractStringStringValuePair
import java.util.*
import kotlin.collections.LinkedHashMap

abstract class ID3Frames : AbstractStringStringValuePair() {

    protected val multipleFrames : TreeSet<String> = TreeSet()

    protected val discardIfFileAlteredFrames : TreeSet<String> = TreeSet()

    protected val supportedFrames : TreeSet<String> = TreeSet()

    protected val extensionFrames : TreeSet<String> = TreeSet()

    protected val commonFrames : TreeSet<String> = TreeSet()

    protected val binaryFrames : TreeSet<String> = TreeSet()

    fun isDiscardIfFileAltered(frameID : String) : Boolean = discardIfFileAlteredFrames.contains(frameID)

    fun isMultipleAllowed(frameID : String) : Boolean = supportedFrames.contains(frameID)

    fun isSupportedFrames(frameID : String) : Boolean = supportedFrames.contains(frameID)

    fun isCommon(frameID : String) : Boolean = commonFrames.contains(frameID)

    fun isBinary(frameID : String) : Boolean = binaryFrames.contains(frameID)

    fun isExtensionFrames(frameID : String) : Boolean = extensionFrames.contains(frameID)

    companion object {
        val convertv22Tov23 : MutableMap<String, String> = LinkedHashMap()
        val convertv23Tov22 : MutableMap<String, String> = LinkedHashMap()
        val forcev22Tov23 : MutableMap<String, String> = LinkedHashMap()
        val forcev23Tov22 : MutableMap<String, String> = LinkedHashMap()

        val convertv23Tov24 : MutableMap<String, String> = LinkedHashMap()
        val convertv24Tov23 : MutableMap<String, String> = LinkedHashMap()
        val forcev23Tov24 : MutableMap<String, String> = LinkedHashMap()
        val forcev24Tov23 : MutableMap<String, String> = LinkedHashMap()

        private fun loadID3v23ID3v24Mapping() {
            convertv23Tov24.put(ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_MUSICBRAINZ, ID3v24Frames.FRAME_ID_TITLE_SORT_ORDER)
            convertv23Tov24.put(ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_MUSICBRANZ, ID3v24Frames.FRAME_ID_ARTIST_SORT_ORDER)
            convertv23Tov24.put(ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_MUSICBRAINZ, ID3v24Frames.FRAME_ID_ALBUM_SORT_ORDER)

            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT, ID3v24Frames.FRAME_ID_RELATIVE_VOLUME_ADJUSTMENT2)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_EQUALISATION, ID3v24Frames.FRAME_ID_EQUALISATION2)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE, ID3v24Frames.FRAME_ID_INVOLVED_PEOPLE)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_TDAT, ID3v24Frames.FRAME_ID_YEAR)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_TIME, ID3v24Frames.FRAME_ID_YEAR)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_TORY, ID3v24Frames.FRAME_ID_ORIGINAL_RELEASE_TIME)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_TRDA, ID3v24Frames.FRAME_ID_YEAR)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_TYER, ID3v24Frames.FRAME_ID_YEAR)

            forcev24Tov23.put(ID3v24Frames.FRAME_ID_RELATIVE_VOLUME_ADJUSTMENT2, ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT)
            forcev24Tov23.put(ID3v24Frames.FRAME_ID_INVOLVED_PEOPLE, ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE)
            forcev24Tov23.put(ID3v24Frames.FRAME_ID_MOOD, ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO)
            forcev24Tov23.put(ID3v24Frames.FRAME_ID_ORIGINAL_RELEASE_TIME, ID3v23Frames.FRAME_ID_V3_TORY)
        }

        private fun loadID3v22ID3v23Mapping() {
            val iterator : Iterator<String>
            var key : String
            var value : String

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ACCOMPANIMENT, ID3v23Frames.FRAME_ID_V3_ACCOMPANIMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ALBUM, ID3v23Frames.FRAME_ID_V3_ALBUM)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ARTIST, ID3v23Frames.FRAME_ID_V3_ARTIST)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_AUDIO_ENCRYPTION, ID3v23Frames.FRAME_ID_V3_AUDIO_ENCRYPTION)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_BPM, ID3v23Frames.FRAME_ID_V3_BPM)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_COMMENT, ID3v23Frames.FRAME_ID_V3_COMMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_COMMENT, ID3v23Frames.FRAME_ID_V3_COMMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_COMPOSER, ID3v23Frames.FRAME_ID_V3_COMPOSER)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_CONDUCTOR, ID3v23Frames.FRAME_ID_V3_CONDUCTOR)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_CONTENT_GROUP_DESC, ID3v23Frames.FRAME_ID_V3_CONTENT_GROUP_DESC)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_COPYRIGHTINFO, ID3v23Frames.FRAME_ID_V3_COPYRIGHTINFO)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ENCODEDBY, ID3v23Frames.FRAME_ID_V3_ENCODEDBY)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_EQUALISATION, ID3v23Frames.FRAME_ID_V3_EQUALISATION)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_EVENT_TIMING_CODES, ID3v23Frames.FRAME_ID_V3_EVENT_TIMING_CODES)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_FILE_TYPE, ID3v23Frames.FRAME_ID_V3_FILE_TYPE)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_GENERAL_ENCAPS_OBJECT, ID3v23Frames.FRAME_ID_V3_GENERAL_ENCAPS_OBJECT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_GENRE, ID3v23Frames.FRAME_ID_V3_GENRE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_HW_SW_SETTINGS, ID3v23Frames.FRAME_ID_V3_HW_SW_SETTINGS)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_INITIAL_KEY, ID3v23Frames.FRAME_ID_V3_INITIAL_KEY)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_IPLS, ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ISRC, ID3v23Frames.FRAME_ID_V3_ISRC)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ITUNES_GROUPING, ID3v23Frames.FRAME_ID_V3_ITUNES_GROUPING)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_LANGUAGE, ID3v23Frames.FRAME_ID_V3_LANGUAGE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_LENGTH, ID3v23Frames.FRAME_ID_V3_LENGTH)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_LINKED_INFO, ID3v23Frames.FRAME_ID_V3_LINKED_INFO)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_LYRICIST, ID3v23Frames.FRAME_ID_V3_LYRICIST)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_MEDIA_TYPE, ID3v23Frames.FRAME_ID_V3_MEDIA_TYPE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_MOVEMENT, ID3v23Frames.FRAME_ID_V3_MOVEMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_MOVEMENT_NO, ID3v23Frames.FRAME_ID_V3_MOVEMENT_NO)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_MPEG_LOCATION_LOOKUP_TABLE, ID3v23Frames.FRAME_ID_V3_MPEG_LOCATION_LOOKUP_TABLE)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_MUSIC_CD_ID, ID3v23Frames.FRAME_ID_V3_MUSIC_CD_ID)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ORGARTIST, ID3v23Frames.FRAME_ID_V3_ORIGARTIST)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ORIG_FILENAME, ID3v23Frames.FRAME_ID_V3_ORIG_FILENAME)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ORIG_LYRICIST, ID3v23Frames.FRAME_ID_V3_ORIG_LYRICIST)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ORIG_TITLE, ID3v23Frames.FRAME_ID_V3_ORIG_TITLE)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_PLAYLIST_DELAY, ID3v23Frames.FRAME_ID_V3_PLAYLIST_DELAY)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_PLAY_COUNTER, ID3v23Frames.FRAME_ID_V3_PLAY_COUNTER)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_PLAY_COUNTER, ID3v23Frames.FRAME_ID_V3_PLAY_COUNTER)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_POPULARIMETER, ID3v23Frames.FRAME_ID_V3_POPULARIMETER)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_PUBLISHER, ID3v23Frames.FRAME_ID_V3_PUBLISHER)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_RECOMMENDED_BUFFER_SIZE, ID3v23Frames.FRAME_ID_V3_RECOMMENDED_BUFFER_SIZE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_RECOMMENDED_BUFFER_SIZE, ID3v23Frames.FRAME_ID_V3_RECOMMENDED_BUFFER_SIZE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_RELATIVE_VOLUME_ADJUSTMENT, ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_REMIXED, ID3v23Frames.FRAME_ID_V3_REMIXED)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_REVERB, ID3v23Frames.FRAME_ID_V3_REVERB)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_SET, ID3v23Frames.FRAME_ID_V3_SET)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_SET_SUBTITLE, ID3v23Frames.FRAME_ID_V3_SET_SUBTITLE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_SYNC_LYRIC, ID3v23Frames.FRAME_ID_V3_SYNC_LYRIC)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_SYNC_TEMPO, ID3v23Frames.FRAME_ID_V3_SYNC_TEMPO)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TDAT, ID3v23Frames.FRAME_ID_V3_TDAT)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TIME, ID3v23Frames.FRAME_ID_V3_TIME)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TITLE_REFINEMENT, ID3v23Frames.FRAME_ID_V3_TITLE_REFINEMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_SYNC_LYRIC, ID3v23Frames.FRAME_ID_V3_SYNC_LYRIC)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_SYNC_TEMPO, ID3v23Frames.FRAME_ID_V3_SYNC_TEMPO)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TDAT, ID3v23Frames.FRAME_ID_V3_TDAT)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TSIZ, ID3v23Frames.FRAME_ID_V3_TSIZ)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TYER, ID3v23Frames.FRAME_ID_V3_TYER)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_UNIQUE_FILE_ID, ID3v23Frames.FRAME_ID_V3_UNIQUE_FILE_ID)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_UNIQUE_FILE_ID, ID3v23Frames.FRAME_ID_V3_UNIQUE_FILE_ID)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_UNSYNC_LYRICS, ID3v23Frames.FRAME_ID_V3_UNSYNC_LYRICS)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_ARTIST_WEB, ID3v23Frames.FRAME_ID_V3_URL_ARTIST_WEB)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_COMMERCIAL, ID3v23Frames.FRAME_ID_V3_URL_COMMERCIAL)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_COPYRIGHT, ID3v23Frames.FRAME_ID_V3_URL_COPYRIGHT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_FILE_WEB, ID3v23Frames.FRAME_ID_V3_URL_FILE_WEB)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_OFFICIAL_RADIO, ID3v23Frames.FRAME_ID_V3_URL_OFFICIAL_RADIO)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_PAYMENT, ID3v23Frames.FRAME_ID_V3_URL_PAYMENT)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_PUBLISHERS, ID3v23Frames.FRAME_ID_V3_URL_PUBLISHERS)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_URL_SOURCE_WEB, ID3v23Frames.FRAME_ID_V3_URL_SOURCE_WEB)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_USER_DEFINED_INFO, ID3v23Frames.FRAME_ID_V3_USER_DEFINED_INFO)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_USER_DEFINED_URL, ID3v23Frames.FRAME_ID_V3_USER_DEFINED_URL)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TITLE, ID3v23Frames.FRAME_ID_V3_TITLE)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_IS_COMPILATION, ID3v23Frames.FRAME_ID_V3_IS_COMPILATION)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_TITLE_SORT_ORDER_ITUNES, ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_ITUNES)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ARTIST_SORT_ORDER_ITUNES, ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_ITUNES)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ALBUM_SORT_ORDER_ITUNES, ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_ITUNES)

            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_ALBUM_ARTIST_SORT_ORDER_ITUNES, ID3v23Frames.FRAME_ID_V3_ALBUM_ARTIST_SORT_ORDER_ITUNES)
            convertv22Tov23.put(ID3v22Frames.FRAME_ID_V2_COMPOSER_SORT_ORDER_ITUNES, ID3v23Frames.FRAME_ID_V3_COMPOSER_SORT_ORDER_ITUNES)

            iterator = convertv22Tov23.keys.iterator()
            while(iterator.hasNext()) {
                key = iterator.next()
                value = convertv22Tov23.get(key)!!
                convertv23Tov22.put(value, key)
            }

            convertv23Tov22.put(ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_MUSICBRAINZ, ID3v22Frames.FRAME_ID_V2_TITLE_SORT_ORDER_ITUNES)
            convertv23Tov22.put(ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_MUSICBRANZ, ID3v22Frames.FRAME_ID_V2_ARTIST_SORT_ORDER_ITUNES)
            convertv23Tov22.put(ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_MUSICBRAINZ, ID3v22Frames.FRAME_ID_V2_ALBUM_SORT_ORDER_ITUNES)

            forcev22Tov23.put(ID3v22Frames.FRAME_ID_V2_ATTACHED_PICTURE, ID3v23Frames.FRAME_ID_V3_ATTACHED_PICTURE)
            forcev23Tov24.put(ID3v23Frames.FRAME_ID_V3_ATTACHED_PICTURE, ID3v22Frames.FRAME_ID_V2_ATTACHED_PICTURE)

        }

        init {
            loadID3v22ID3v23Mapping()
            loadID3v23ID3v24Mapping()
        }

    }


}