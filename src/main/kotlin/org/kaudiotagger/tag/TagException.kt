package org.kaudiotagger.tag

//TODO 안정성이 확보되지 않았으므로 추후에 꼭 시험해볼 것
open class TagException(msg : String, ex : Throwable) : Exception(msg, ex) {

}