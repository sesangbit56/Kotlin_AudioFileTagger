package org.kaudiotagger.tag

//TODO 안정성이 확보되지 않았으므로 추후에 꼭 확인할 것
open class TagNotFoundException(msg : String, ex : Throwable) : TagException(msg, ex) {
}