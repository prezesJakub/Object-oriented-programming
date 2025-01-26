import java.util.UUID

interface WorldMap {

    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun isOccupied(position: Vector2d): Boolean

    fun objectAt(position: Vector2d): Animal?

    fun getCurrentBounds(): Boundary

    fun getElements(): List<Animal>
}