package esgi.com
package main

import main.Orientation.Orientation

trait OrientationModifier {
  def getNewOrientation(orientations: List[Orientation], currentOrientation: Int): Orientation
}

case class LeftOrientationModifier() extends OrientationModifier {
  override def getNewOrientation(orientations: List[Orientation], currentOrientationIndex: Int): Orientation =
    if (currentOrientationIndex > 0) {
      orientations(currentOrientationIndex - 1)
    } else {
      orientations.last
    }
}

case class RightOrientationModifier() extends OrientationModifier {
  override def getNewOrientation(orientations: List[Orientation], currentOrientationIndex: Int): Orientation =
    if (currentOrientationIndex < orientations.length) {
      orientations(currentOrientationIndex + 1)
    } else {
      orientations.head
    }
}
