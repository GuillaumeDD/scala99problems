/*******************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package multiwayTree.P73

import multiwayTree.MTree
import org.scalatest.Args
import scala.util.parsing.combinator.JavaTokenParsers

class sol01 extends P73 {
  def lipsy(t: MTree[Char]): String =
    if (t.children.isEmpty) {
      t.value.toString
    } else {
      s"(${t.value} ${t.children.map(lipsy(_)).mkString(" ")})"
    }

  // Usage of Scala combinator
  object TreeParser extends JavaTokenParsers {
    // Define the expected value of a node (here a Char)
    def value: Parser[Char] = """[a-zA-Z]""".r ^^ { _.head }

    def simpleTree: Parser[MTree[Char]] = value ^^ { MTree(_) }

    // Define the string representation of a non-leaf node
    def tree: Parser[MTree[Char]] =
      simpleTree | ("(" ~> value ~ rep(" " ~> simpleTree | tree) <~ ")") ^^ {
        case value ~ children =>
          if (children.isEmpty) {
            MTree(value)
          } else {
            MTree(value, children)
          }
      }

    def apply(input: String): MTree[Char] = parseAll(tree, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  def lipsyStringToMTree(t: String): MTree[Char] =
    TreeParser(t)
}
