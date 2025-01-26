enum class MapDirection {
    NORTH, SOUTH, EAST, WEST;

    override fun toString(): String = when (this) {
        NORTH -> "N"
        SOUTH -> "S"
        EAST -> "E"
        WEST -> "W"
    }

    fun next(): MapDirection = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun previous(): MapDirection = when (this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }
}