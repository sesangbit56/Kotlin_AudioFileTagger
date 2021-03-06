package org.kaudiotagger.audio

import java.io.File
import java.util.logging.Logger

import org.kaudiotagger.audio.AudioHeader
import org.kaudiotagger.audio.exceptions.NoReadPermissionsException
import org.kaudiotagger.audio.generic.Permissions
import org.kaudiotagger.tag.Tag
import java.io.FileNotFoundException
import java.io.RandomAccessFile
import java.lang.RuntimeException
import java.nio.file.Files
import java.nio.file.Path

class AudioFile() {


    val logger: Logger = Logger.getLogger("org.kaudiotagger.audio")


    //TODO protected 접근자를 지워야지만 진행할 수 있었다. 다른 방법은 없는 것인가?
    var file: File? = null
        get() = this.file

    var audioHeader: AudioHeader? = null

    var tag : Tag? = null

    var extension: String? = null

    constructor(f: File, audioHeader: AudioHeader, tag: Tag) {
        this.file = f
        this.audioHeader = audioHeader
        this.tag = tag
    }

    constructor(s: String, audioHeader: AudioHeader, tag: Tag) {
        this.file = File(s)
        this.audioHeader = audioHeader
        this.tag = tag
    }

    //TODO AudioFileIO 구현합시다아
    fun commit() = AudioFileIO.write(this)

    //TODO AudioFileIO 구현합시다아
    fun delete() = AudioFileIO.delete(this)

    override fun toString(): String = "AudioFile " + file?.absolutePath + "  --------\n" + audioHeader.toString() + "\n" + if (tag == null) "" else tag.toString() + "\n-------------------"

    fun checkFileExists(file: File) {
        logger.config("Reading file :" + "path" + file.path + ":abs:" + file.absolutePath)
        if (!file.exists()) {
            logger.severe("Unablue to fine:" + file.path)
            //TODO 에러메시지 구현 후 구현할 것
            throw FileNotFoundException()
        }
    }

    protected fun checkFilePermissions(file: File, readOnly: Boolean): RandomAccessFile? {
        val path: Path = file.toPath()
        var newFile: RandomAccessFile? = null
        checkFileExists(file)

        if (readOnly) {
            if (Files.isReadable(path)) {
                logger.severe("Unable to read file:" + path)
                //TODO Permissions 클래스 구현합시다아
                logger.severe(Permissions.displayPermissions(path))
                //TODO NoReadPermissionsException 구현합시다아
                throw NoReadPermissionsException()
            }
            newFile = RandomAccessFile(file, "r")
        } else {
            //TODO TagOptionSingleton 구현과 함께 이하의 코드를 구현합시다
        }
        return newFile
    }

    fun displayStructureAsXML(): String = ""

    fun displayStructureAsPlainText(): String = ""

    fun createDefaultTag(): Tag {
        var extension = this.extension
        if(extension == null) {
            val fileName : String? = file?.name
            extension = fileName?.substring(fileName.lastIndexOf(('.')+1))
            this.extension = extension
        }
        when(SupportedFileFormat.OGG.getFilesuffix()){
            "flac" -> return FlacTag(VorbisCommentTag.createNewTag(), ArrayList< MetadataBlockDataPicture >())
            "ogg" -> return VorbisCommentTag.createNewTag()
            "mp4" -> return Mp4Tag()
            "m4a" -> return Mp4Tag()
            "m4p" -> return Mp4Tag()
            "wma" -> return AsfTag()
            //TODO 이것 좀 끝내자 제발...
            else -> throw RuntimeException("Unable to create default tag for this file format")
        }
    }

    fun getTagOrCreateDefault() : Tag {
        val tag : Tag? = this.tag
        if(tag == null){
            return createDefaultTag()
        }
        return tag
    }

    fun getTagOrCreateAndSetDefault() : Tag {
        val tag : Tag = getTagOrCreateDefault()
        this.tag = tag
        return tag
    }

    fun getTagAndConvertOrCreateAndSetDefault() : Tag {
        val tag : Tag = getTagOrCreateDefault()
        //TODO 이 메소드가 Wav와 Aif 파일에서도 작동할 수 있도록 합시다아
        if(tag is AbstractID3v2Tag) {
            //TODO 나머지 채웁시다아ㅏ
        }
        return this.tag
    }

    companion object {
        fun getBaseFilename(file: File): String {
            val index: Int = file.name.toLowerCase().lastIndexOf(".")
            if (index > 0) {
                return file.name.substring(0, index)
            }
            return file.name
        }
    }

    /*
    fun convertID3Tag(tag : AbstractID3v2Tag, id3V2Version : ID3V2Version) : AbstractId3v2Tag {
        //TODO 이곳이 2중 when문으로 커버가 될까? 일단 ifelse문으로 해보고 나중에 다시 작성해보자
    }
    */



}

