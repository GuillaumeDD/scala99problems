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
package binaryTree.P69

import org.scalatest.Args
import binaryTree.Tree
import binaryTree.Node
import binaryTree.End
import scala.util.parsing.combinator.JavaTokenParsers

class sol01 extends P69 {
  def toDotstring(t: Tree[Char]): String =
    t match {
      case End => "."
      case Node(value, left, right) =>
        value + toDotstring(left) + toDotstring(right)
    }

  // Usage of Scala combinator
  object TreeParser extends JavaTokenParsers {
    // Define the expected value of a node (here a Char)
    def value: Parser[Char] = """[a-zA-Z]""".r ^^ { _.head }

    // Define the string representation of an End (here, an empty string)
    def end: Parser[Tree[Char]] = """\.""".r ^^ { s => End }

    // Define the string representation of a non-leaf node
    def complexNode: Parser[Tree[Char]] =
      (value ~ tree ~ tree) ^^ {
        case value ~ left ~ right =>
          Node(value, left, right)
      }

    // Define the string representation of a tree
    def tree: Parser[Tree[Char]] = complexNode | end

    def apply(input: String): Tree[Char] = parseAll(tree, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  def fromDotstring(s: String): Tree[Char] = TreeParser(s)
}
