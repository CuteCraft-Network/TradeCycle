package net.cutecraft.core.common

data class ComparableVersion(val rawVersion: String) : Comparable<ComparableVersion> {

    private val parts = rawVersion.split(".").map { it.toIntOrNull() ?: 0 }

    override fun compareTo(other: ComparableVersion): Int {
        val maxLength = maxOf(parts.size, other.parts.size)
        for (i in 0 until maxLength) {
            val thisPart = parts.getOrElse(i) { 0 }
            val otherPart = other.parts.getOrElse(i) { 0 }
            if (thisPart != otherPart) {
                return thisPart - otherPart
            }
        }
        return 0
    }

}