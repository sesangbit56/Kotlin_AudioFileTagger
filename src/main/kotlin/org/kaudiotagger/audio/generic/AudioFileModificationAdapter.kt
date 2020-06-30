package org.kaudiotagger.audio.generic

import org.kaudiotagger.audio.AudioFile
import org.kaudiotagger.audio.exceptions.ModifyVetoException
import java.io.File

class AudioFileModificationAdapter : AudioFileModificationListener {

    override fun fileModified(original: AudioFile, temporary: File) {
        //할 일이 없다고 합니다
    }

    override fun fileOperationFinished(result: File) {
        //할 일이 없다고 합니다
    }

    override fun fileWillBeModified(file: AudioFile, delete: Boolean) {
        //할 일이 없다고 합니다
    }

    override fun vetoThrown(cause: AudioFileModificationListener, original: AudioFile, veto: ModifyVetoException) {
        //할 일이 없다고 합니다
    }
}