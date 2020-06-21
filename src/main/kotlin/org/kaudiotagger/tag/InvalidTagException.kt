package org.kaudiotagger.tag

//TODO 안정성 확보
open class InvalidTagException(msg : String, ex : Throwable) : TagException(msg, ex) {

}