package esgi.com
package main

import main.Action.Action

object Action extends Enumeration {
  type Action = Value
  val G, D, A = Value
}


case class ActionFactory() {
  def get(actionLetter: String): Action = actionLetter match {
    case "A" => Action.A
    case "G" => Action.G
    case "D" => Action.D
    case _ => throw DonneesIncorectesException("Le type d'action passé n'est pas A, G ou D.")
  }
}
