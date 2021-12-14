package esgi.com.main

import esgi.com.main.Orientation.Orientation

trait PositionActuator {
  def generateNewPosition(position: Position): Position
}


class Translator(coordinateModifierMap: Map[Orientation, CoordinateModifier]) extends PositionActuator {
  def generateNewPosition(position: Position): Position =
    Position(coordinateModifierMap(position.orientation).getNewCoordinate(position.coordinate), orientation = position.orientation)
}

object Translator {
  def apply(): Translator = new Translator(Map(
    Orientation.SOUTH -> SouthCoordinateModifier(),
    Orientation.EAST -> EastCoordinateModifier(),
    Orientation.NORTH -> NorthCoordinateModifier(),
    Orientation.WEST -> WestCoordinateModifier(),
  ))
}

class Rotator(orientationModifier: OrientationModifier, orientations: List[Orientation]) extends PositionActuator {
  override def generateNewPosition(position: Position): Position = {
    val newOrientation = orientationModifier.getNewOrientation(orientations, orientations.indexOf(position.orientation))
    Position(position.coordinate, newOrientation)
  }
}

object Rotator {
  private val orientations = Orientation.NORTH :: Orientation.EAST :: Orientation.SOUTH :: Orientation.WEST :: Nil

  def leftRotator(): Rotator = new Rotator(LeftOrientationModifier(), orientations)

  def rightRotator(): Rotator = new Rotator(RightOrientationModifier(), orientations)

}



