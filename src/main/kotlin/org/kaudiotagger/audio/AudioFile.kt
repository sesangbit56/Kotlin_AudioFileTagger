package org.kaudiotagger.audio

import java.io.File
import java.util.logging.Logger

import org.kaudiotagger.audio.AudioHeader
import java.io.FileNotFoundException
import java.io.RandomAccessFile
import java.lang.RuntimeException
import java.nio.file.Files
import java.nio.file.Path

class AudioFile() {

    val logger: Logger = Logger.getLogger("org.jaudiotagger.audio")

    protected var file: File?
        set(value) {
            field = value
        }

    protected var audioHeader: AudioHeader?
        set(value) {
            field = value
        }

    protected var tag: Tag?

    protected var extension: String?

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
        val tag : Tag = this.tag
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

    public getTagAndConvertOrCreateAndSetDefault() : Tag {

    }
}

