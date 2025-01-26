import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : BehaviorSpec({

    Given("an empty BouncyMap") {
        val mapWidth = 10
        val mapHeight = 10
        val map = BouncyMap(mapWidth, mapHeight)
        Then("it should have no animals") {
            map.getElements().size shouldBe 0
        }
        Then("canMoveTo should work correctly") {
            map.canMoveTo(Vector2d(2, 1)) shouldBe true
            map.canMoveTo(Vector2d(0, 0)) shouldBe true
            map.canMoveTo(Vector2d(9, 7)) shouldBe true
            map.canMoveTo(Vector2d(10, 7)) shouldBe false
            map.canMoveTo(Vector2d(2, 10)) shouldBe false
            map.canMoveTo(Vector2d(32, 12)) shouldBe false
        }
        Then("getCurrentBounds should work correctly") {
            map.getCurrentBounds() shouldBe Boundary(Vector2d(0,0), Vector2d(9, 9))
        }
    }

    Given("a BouncyMap with animal placed on it") {
        val mapWidth = 10
        val mapHeight = 10
        val map = BouncyMap(mapWidth, mapHeight)
        val animal = Animal(Vector2d(3, 3), MapDirection.NORTH)
        map.place(animal)

        Then("objectAt should return animal for its position") {
            map.objectAt(Vector2d(3, 3)) shouldBe animal
        }

        Then("isOccupied should work correctly") {
            map.isOccupied(Vector2d(3, 3)) shouldBe true
            map.isOccupied(Vector2d(2, 1)) shouldBe false
        }

        When("animal trying to move") {
            map.move(animal, MoveDirection.FORWARD)
            map.move(animal, MoveDirection.RIGHT)
            Then("animal position should be correct") {
                animal.position shouldBe Vector2d(3, 4)
                animal.orientation shouldBe MapDirection.EAST
            }
        }
    }

    Given("a BouncyMap with two animals trying to occupy the same position") {
        val mapWidth = 10
        val mapHeight = 10
        val map = BouncyMap(mapWidth, mapHeight)
        val animal1 = Animal(Vector2d(3, 3), MapDirection.NORTH)
        val animal2 = Animal(Vector2d(3, 3), MapDirection.NORTH)
        map.place(animal1)

        Then("placing another animal should bounce it") {
            map.place(animal2)
            animal1.position shouldBe Vector2d(3, 3)
            animal2.position shouldNotBe Vector2d(3, 3)
        }
    }
})
