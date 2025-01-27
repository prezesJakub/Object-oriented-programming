class BouncyMap(private val width: Int, private val height: Int) : WorldMap {
    private val animals = mutableMapOf<Vector2d, Animal>()

    fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0 until width && position.y in 0 until height
    }

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }

    override fun getCurrentBounds(): Boundary {
        return Boundary(Vector2d(0, 0), Vector2d(width-1, height-1))
    }

    override fun place(animal: Animal) {
        val currentPosition = animal.position

        if(!canMoveTo(currentPosition)) {
            throw IllegalArgumentException("Position $currentPosition is out of bounds")
        }

        val existingAnimal = animals[currentPosition]

        if (existingAnimal != null) {
            val newPosition = animals.randomFreePosition(Vector2d(width, height))
                ?: animals.randomPosition()?.also{ animals.remove(it) }

            newPosition?.let {
                animal.position = it
            } ?: throw IllegalStateException("No free position to place animal")
        }

        animals[animal.position] = animal
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val currentPosition = animal.position
        animals.remove(currentPosition)

        animal.move(direction)
        val newPosition = animal.position

        if(objectAt(newPosition) != null) {
            val bouncePosition = animals.randomFreePosition(Vector2d(width, height))
                ?: animals.randomPosition()?.also{ animals.remove(it) }
            bouncePosition?.let {
                animal.position = it
            } ?: throw IllegalStateException("No free position to bounce animal")
        }
        animals[animal.position] = animal
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun getElements(): List<Animal> {
        return animals.values.toList()
    }
}