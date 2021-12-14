package esgi.com
package main

trait CoordinateModifier {
  def getNewCoordinate(coordinate: Coordinate): Coordinate
}

case class NorthCoordinateModifier() extends CoordinateModifier {
  override def getNewCoordinate(coordinate: Coordinate): Coordinate = Coordinate(coordinate.x, coordinate.y + 1)
}

case class SouthCoordinateModifier() extends CoordinateModifier {
  override def getNewCoordinate(coordinate: Coordinate): Coordinate = Coordinate(coordinate.x, coordinate.y - 1)
}

case class WestCoordinateModifier() extends CoordinateModifier {
  override def getNewCoordinate(coordinate: Coordinate): Coordinate = Coordinate(coordinate.x - 1, coordinate.y)
}

case class EastCoordinateModifier() extends CoordinateModifier {
  override def getNewCoordinate(coordinate: Coordinate): Coordinate = Coordinate(coordinate.x + 1, coordinate.y)
}