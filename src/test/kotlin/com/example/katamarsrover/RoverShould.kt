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
        }
        return currentPosition().x.toString() + ":" + currentPosition().y.toString() + ":" + direction.value
    }

}

class Board(var height: Int, var width: Int) {}

data class Position(var x: Int, var y: Int) {
}

data class Direction (var value: String){

    val directions = mapOf(
        "N" to "N,W,E",
        "E" to "E,N,S",
        "S" to "S,E,W",
        "W" to "W,S,N"
    )
    fun turnRight(): Direction {
        var right: String = directions.get(this.value)!!.split(",")[2]
        return Direction(right)
    }

    fun turnLeft(): Direction {
        var left: String = directions.get(this.value)!!.split(",")[1]
        return Direction(left)
    }

}




