import kotlin.random.Random

fun Map<Vector2d, *>.randomPosition(): Vector2d? {
    return keys.randomOrNull()
}

fun Map<Vector2d, *>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val allPositions = (0 until mapSize.x).flatMap { x ->
        (0 until mapSize.y).map { y -> Vector2d(x, y) }
    }

    val occupiedPositions = keys
    val freePositions = allPositions - occupiedPositions

    return freePositions.randomOrNull()
}