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
package binaryTree.P63

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.Node
import binaryTree.End

trait P63 extends ExerciseTemplate {
  /*
    P63 (**) Construct a complete binary tree.
    A complete binary tree with height H is defined as follows: The levels 1,2,3,...,H-1 contain the maximum number of nodes 
    (i.e 2(i-1) at the level i, note that we start counting the levels from 1 at the root). 
    In level H, which may contain less than the maximum possible number of nodes, all the nodes are "left-adjusted". 
    This means that in a levelorder tree traversal all internal nodes come first, the leaves come second, 
    and empty successors (the Ends which are not really nodes!) come last.

    Particularly, complete binary trees are used as data structures (or addressing schemes) for heaps.

    We can assign an address number to each node in a complete binary tree by enumerating the nodes in levelorder, starting at 
    the root with number 1. 
    In doing so, we realize that for every node X with address A the following property holds: 
    The address of X's left and right successors are 2*A and 2*A+1, respectively, supposed the successors do exist. 
    This fact can be used to elegantly construct a complete binary tree structure. 
    Write a method completeBinaryTree that takes as parameters the number of nodes and the value to put in each node.

    scala> Tree.completeBinaryTree(6, "x")
    res0: Node[String] = T(x T(x T(x . .) T(x . .)) T(x T(x . .) .))
 */
  val name = "Construct a complete binary tree (P63)"
  def completeBinaryTree[T](n: Int, elt: T): Tree[T]

  test("Invoking completeBinaryTree with n < 0 should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      completeBinaryTree(-1, 'a)
    }
  }

  test("Invoking completeBinaryTree should constructor a complete binary tree with n nodes") {
    // 0 node
    assert(completeBinaryTree(0, 'a) == End)

    // 1 nodes
    assert(completeBinaryTree(1, 'a) == Node('a))

    // 2 nodes
    //        'a
    //        /
    //      'a
    assert(completeBinaryTree(2, 'a) == Node('a, Node('a), End))

    // 3 nodes
    //        'a
    //        / \
    //      'a   'a 
    assert(completeBinaryTree(3, 'a) == Node('a, Node('a), Node('a)))

    // 4 nodes
    //        'a
    //       /   \
    //     'a    'a         
    //     /    
    //   'a       
    assert(completeBinaryTree(4, 'a) == Node('a, Node('a, Node('a), End), Node('a, End, End)))

    // 5 nodes
    //        'a
    //       /   \
    //     'a    'a         
    //     / \   
    //   'a  'a     
    assert(completeBinaryTree(5, 'a) == Node('a, Node('a, Node('a), Node('a)), Node('a, End, End)))

    // 6 nodes
    //        'a
    //       /   \
    //     'a    'a         
    //     / \   /
    //   'a  'a 'a
    assert(completeBinaryTree(6, 'a) == Node('a, Node('a, Node('a), Node('a)), Node('a, Node('a), End)))

    // 7 nodes
    //        'a
    //       /   \
    //     'a    'a         
    //     / \   / \
    //   'a  'a 'a  'a
    assert(completeBinaryTree(7, 'a) == Node('a, Node('a, Node('a), Node('a)), Node('a, Node('a), Node('a))))

    // 8 nodes
    //        'a
    //       /   \
    //     'a    'a         
    //     / \   / \
    //   'a  'a 'a  'a
    //   /
    // 'a
    assert(completeBinaryTree(8, 'a) == Node('a, Node('a, Node('a, Node('a), End), Node('a)), Node('a, Node('a), Node('a))))

  }

}
