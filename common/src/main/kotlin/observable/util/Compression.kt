package observable.util

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object Compression {
    fun gzip(content: String): ByteArray {
        val bos = ByteArrayOutputStream()
        GZIPOutputStream(bos).use { it.write(content.toByteArray(Charsets.UTF_8)) }
        return bos.toByteArray()
    }

    fun ungzip(content: ByteArray): String {
        return GZIPInputStream(ByteArrayInputStream(content)).bufferedReader(Charsets.UTF_8).use { it.readText() }
    }
}
