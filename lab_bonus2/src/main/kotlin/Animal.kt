class Animal(
    var position: Vector2d = Vector2d(2, 2),
    var orientation: MapDirection = MapDirection.NORTH
) {
    fun isAt(position: Vector2d): Boolean = this.position == position

    fun move(direction: MoveDirection) {
        val newPosition = when (direction) {
            MoveDirection.FORWARD -> position + orientation.toUnitVector()
            MoveDirection.BACKWARD -> position - orientation.toUnitVector()
            MoveDirection.RIGHT -> {
                orientation = orientation.next()
                position
            }

            MoveDirection.LEFT -> {
                orientation = orientation.previous()
                position
            }
        }
    }

    override fun toString(): String = orientation.toString()
}