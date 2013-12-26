package P09

object sol01 extends P09 {
  def pack[T](l: List[T]): List[List[T]] =
    // Départ de la fin de la liste
    (l foldRight List(List[T]()))((head, llist) =>
      if (llist.head.isEmpty || llist.head.head == head) {
        // Ajout de l'élément en tête de liste
        (head +: llist.head) +: llist.tail
      } else {
        (head +: List[T]()) +: llist
      })
}