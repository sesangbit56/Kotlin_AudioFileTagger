package org.kaudiotagger.audio.aiff.chunk

import org.kaudiotagger.audio.iff.ChunkSummary
import org.kaudiotagger.tag.aiff.AiffTag

class AiffChunkSummary {

    companion object {
        fun isOnlyMetadataTagsAfterStartingMetadataTag(tag : AiffTag) : Boolean {
            var firstId3Tag : Boolean = false
            for(cs : ChunkSummary in tag.getChunkSummaryList()) {
                if(firstId3Tag) {
                    if(!cs.getChunkId().equals(AiffChunkType.TAG.code)) return false
                } else {
                    if(cs.getFileStartLocation() == tag.getStartLocationInFileOfId3Chunk()) {
                        firstId3Tag = true
                    }
                }
            }

            if(firstId3Tag == true) return true
            return true
        }

        fun getChunkBeforeStartingMetadataTag(tag : AiffTag) : ChunkSummary? {
            for(i : Int in 0 until tag.getChunkSummaryList().size) {
                val cs : ChunkSummary = tag.getChunkSummaryList().get(i)
                if(cs.getFileStartLocation() == tag.getStartLocationInFileOfId3Chunk()) return tag.getChunkSummaryList().get(i - 1)
            }
            return null
        }
    }
}