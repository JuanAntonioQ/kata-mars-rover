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
}

class Rover(var board: Board, var position: Position, var direction: Direction) {

    fun currentPosition(): Position{
        return position
    }

    fun currentDirection(): Direction {
        return direction
    }

}

class Board(var height: Int, var width: Int) {}

data class Position(var x: Int, var y: Int) {
}

data class Direction (var direction: String){}




