package esgi.com


import main.{ActionFactory, Coordinate, Mower, OrientationFactory, Position}
import main.Action.Action

import scala.collection.immutable.Queue
import scala.io.Source

case class Environment(field: Field, mowers: List[Mower]) {

}


case class Field(x: Integer, y: Integer) {

}

case class InputParser() {
  def parse(filename: String): Environment = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines()

    val field = this.parseRawFieldParameters(lines.next())

    val env = Environment(field, this.parseMowers(lines.toList))

    bufferedSource.close()

    env
  }

  private def parseRawFieldParameters( rawFieldParameters: String): Field = {
    val valuesAsStrings = rawFieldParameters.split(" ")
    if (valuesAsStrings.length != 2) {
      throw DonneesIncorectesException("Il faut deux nombres pour spécifier la taille du terrain")
    }

    Field(valuesAsStrings(0).toInt, valuesAsStrings(1).toInt)
  }

  private def parseMowers(parametersAsString: List[String]): List[Mower] = parametersAsString match {
    case positionString :: Nil => throw DonneesIncorectesException("Les valeurs pour les tondeuses viennent par pair")
    case positionString :: actionsString:: rest => createMowersFromPosition(positionString, actionsString) :: parseMowers(rest)
    case _ => Nil
  }

  private def createMowersFromPosition(positionInString: String, actionsInString: String): Mower =
    Mower(
      actions = actionsAsStringToActionQueue(actionsInString.split("").toList),
      position = parsePosition(positionInString)
      )

  private def actionsAsStringToActionQueue(actionsStrings: List[String]): Queue[Action] = actionsStrings match {
    case head :: rest => Queue(ActionFactory().get(head)).enqueueAll(actionsAsStringToActionQueue(rest))
    case _ => Queue[Action]()
  }

  private def parsePosition(positionInString: String): Position = {
    val positionParameters = positionInString.split(" ")
    if (positionParameters.length != 3) {
      throw DonneesIncorectesException("Vous devez fournir trois informations de position")
    }

    Position(
      Coordinate(positionParameters(0).toInt, positionParameters(1).toInt),
      OrientationFactory().get(positionParameters(2))
    )
  }


}





case class DonneesIncorectesException(message: String) extends Exception