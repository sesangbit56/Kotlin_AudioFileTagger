package org.kaudiotagger.tag.images

import java.io.File

interface Artwork {

    fun getBinaryData() : Array<Byte>

    fun setBinaryData(binaryData : Array<Byte>)

    fun getMimeType() : String

    fun setMimeType(mimeType : String)

    fun getDescription() : String

    fun getHeight() : Int

    fun getWidth() : Int

    fun setDescription(description : String)

    fun setImageFromData() : Boolean

    fun getImage() : Any

    fun isLinked() : Boolean

    fun setLinked(linked : Boolean)

    fun getImageUrl() : String

    fun setImageUrl(imageUrl : String)

    fun getPictureType(pictureType : Int)

    fun setFromFile(File : File)

    fun setFromMetadataBlockDataPicture(coverArt : MetadataBlockDataPicture)

    fun setWidth(width : Int)

    fun setHight(height : Int)

}