package com.example.katamarsrover

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RoverShould {

    @Test
    fun `start facing to the north in a specific position`(){
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals(rover.currentPosition(), Position(0, 0))
        Assertions.assertEquals(rover.currentDirection(), Direction("N"))
    }

    @Test
    fun `start facing to the north and rotate once to the right`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:E", rover.execute("R"))
    }

    @Test
    fun `start facing to the north and turn right twice`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)


        Assertions.assertEquals("0:0:S", rover.execute("RR"))
    }

    @Test
    fun `start facing to the north and turn three times to the right`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)


        Assertions.assertEquals("0:0:W", rover.execute("RRR"))
    }

    @Test
    fun `start facing to the north and turn left once`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:W", rover.execute("L"))
    }

    @Test
    fun `start facing to the north and turn left twice`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:S", rover.execute("LL"))
    }

    @Test
    fun `start facing to the north and turn three times to the left`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:E", rover.execute("LLL"))
    }

    @Test
    fun `move one position to the north`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:1:N", rover.execute("M"))
    }

    @Test
    fun `move two positions to the north`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:2:N", rover.execute("MM"))
    }

    @Test
    fun `move from top to bottom on the board when facing to the north and it reaches the board boundary`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:N", rover.execute("MMMMMMMMMM"))
    }

    @Test
    fun `move one position to the east`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("1:0:E", rover.execute("RM"))
    }

    @Test
    fun `move two positions to the east`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("2:0:E", rover.execute("RMM"))
    }

    @Test
    fun `move from right to left on the board when facing to the east and it reaches the board boundary`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 0)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:E", rover.execute("RMMMMMMMMMM"))
    }

    @Test
    fun `move one position to the south`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 1)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:S", rover.execute("RRM"))
    }

    @Test
    fun `move two positions to the south`() {
        var board: Board = Board(10, 10)
        var position: Position = Position(0, 2)
        var direction: Direction = Direction("N")
        var rover: Rover = Rover(board, position, direction)

        Assertions.assertEquals("0:0:S", rover.execute("RRMM"))
    }
}

class Rover(var board: Board, var position: Position, var direction: Direction) {

    fun currentPosition(): Position{
        return position
    }

    fun currentDirection(): Direction {
        return direction
    }

    fun execute(commands: String): String{
        for (command in commands) {
            if (command == 'R') {
                direction = direction.turnRight()
            }
            if (command == 'L') {
                direction = direction.turnLeft()
            }
            if (command == 'M') {
                move()
            }
        }
        return currentPosition().x.toString() + ":" + currentPosition().y.toString() + ":" + direction.value
    }

    fun move() {
        var nextPosition = board.getNextPosition(this.position, this.direction)
        this.position = nextPosition
    }

}

class Board(var height: Int, var width: Int) {
    fun getNextPosition(position: Position, direction: Direction): Position {
        var x: Int = position.x
        var y: Int = position.y

        if (direction == Direction("N")) {
            y = (y + 1) % height
        }
        if (direction == Direction("E")) {
            x = (x + 1) % width
        }
        if (direction == Direction("S")) {
            y = if (y > 0) y - 1 else height - 1
        }

        return Position(x, y)
    }
}

data class Position(var x: Int, var y: Int) {
}

data class Direction (var value: String){

    val directions = mapOf(
        "N" to "W,E",
        "E" to "N,S",
        "S" to "E,W",
        "W" to "S,N"
    )
    fun turnRight(): Direction {
        var right: String = directions.get(this.value)!!.split(",")[1]
        return Direction(right)
    }

    fun turnLeft(): Direction {
        var left: String = directions.get(this.value)!!.split(",")[0]
        return Direction(left)
    }

}




