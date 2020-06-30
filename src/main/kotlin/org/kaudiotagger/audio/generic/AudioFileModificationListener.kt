package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioFile
import org.kaudiotagger.audio.exceptions.ModifyVetoException
import java.io.File

interface AudioFileModificationListener {

    fun fileModified(original : AudioFile, temporary : File)

    fun fileOperationFinished(result : File)

    fun fileWillBeModified(file : AudioFile, delete : Boolean)

    fun vetoThrown(cause : AudioFileModificationListener, original : AudioFile, veto : ModifyVetoException)
}