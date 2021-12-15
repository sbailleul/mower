package esgi.com
package main

import main.Action.Action

import scala.collection.immutable.Queue

case class Mower(actions: Queue[Action], positionActuators: Map[Action, PositionActuator], position: Position) {
  private var _position = position

  def hasFinish: Boolean = actions.nonEmpty

  def getNewPosition: Position = {
    val currentAction = actions.dequeue._1
    positionActuators(currentAction).generateNewPosition(position)
  }

  def setNewPosition(newPosition: Position): Unit = {
    _position = newPosition
  }
}

object Mower {
  def apply(actions: Queue[Action], position: Position): Mower = {
    val positionActuators = Map(Action.A -> Translator(), Action.D -> Rotator.rightRotator(), Action.G -> Rotator.leftRotator())
    new Mower(actions, positionActuators, position)
  }
}