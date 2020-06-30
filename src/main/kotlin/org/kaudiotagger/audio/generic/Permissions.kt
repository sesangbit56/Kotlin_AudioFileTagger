package org.kaudiotagger.audio.generic

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.*
import java.util.logging.Logger

//TODO var & val 선택 확인바람
class Permissions {

    companion object {
        val logger: Logger = Logger.getLogger("org.kaudiotagger.audio.generic")

        fun displayPermissions(path: Path): String {
            var sb: StringBuilder = StringBuilder()
            sb.append("File $path permissions\n")
            //TODO class를 따로 선언하지 않고 매개변수로 넣을 방법 모색 바람
            val clazz: Class<AclFileAttributeView> = AclFileAttributeView::class.java
            try {
                //TODO 중괄호를 치는 것이 꼭 필요한 일인가? 확인바람
                val aView: AclFileAttributeView = Files.getFileAttributeView(path, clazz)
                if (aView != null) {
                    sb.append("owner:${aView.owner.name}\n")
                    for (acl: AclEntry in aView.acl) {
                        sb.append("$acl\n")
                    }
                }

                var pView: PosixFileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView::class.java)
                if (pView != null) {
                    var pfa: PosixFileAttributes = pView.readAttributes()
                    sb.append(":owner:${pfa.owner().name}:group:${pfa.group().name}:${PosixFilePermissions.toString(pfa.permissions())}\n")
                }
            } catch (ioe: IOException) {
                logger.severe("Unable to read permissions for:${path}")
            }
            return sb.toString()
        }
    }
}