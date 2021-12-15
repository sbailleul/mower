package esgi.com
package main

import main.Orientation.Orientation

object Orientation extends Enumeration {
  type Orientation = Value
  val NORTH, EAST, SOUTH, WEST = Value
}


case class OrientationFactory() {
  def get(orientationInString: String): Orientation = orientationInString match {
    case "N" => Orientation.NORTH
    case "E" => Orientation.EAST
    case "S" => Orientation.SOUTH
    case "W" => Orientation.WEST
    case _ => throw DonneesIncorectesException("La lettre passée pour l'orientation initiale du Robot n'est pas N, E, S ou W")
  }
}
