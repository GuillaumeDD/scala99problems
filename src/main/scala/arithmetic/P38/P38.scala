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
package arithmetic.P38
import util.tictac

object P38 extends App {
  /*
  P38 (*) Compare the two methods of calculating Euler's totient function.
  Use the solutions of problems P34 and P37 to compare the algorithms. Try to calculate phi(10090) as an example.
  */
  tictac("P34-sol01") {
    (new arithmetic.P34.sol01()).totient(10090)
  }

  tictac("P37-sol01") {
    (new arithmetic.P37.sol01()).totient(10090)
  }
}
