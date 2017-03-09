public class StackDeque<E> {
   private ArrayStack<E> frontStack = new ArrayStack();
   private ArrayStack<E> endStack = new ArrayStack();
   
   // default compiler
   public StackDeque() { }
   // check if deck is empty
   public boolean isEmpty () { return (this.size() == 0); }
   // function used in other methods to move elements to the other ArrayStack
   public void moveEnd() {
      for (int i = 0; i < endStack.size(); ++i) {
         frontStack.push(endStack.pop());
      }
   }
   // function used in other methods to move elements to the other ArrayStack
   public void moveFront() {
      for (int i = 0; i < frontStack.size(); ++i) {
         endStack.push(frontStack.pop());
      }
   }
   // return first element
   public E first () {
      // if empty then move elements and recall this method
      if (frontStack.size() == 0) {
         this.moveEnd();
      }
      // return the top front element
      return frontStack.top();
   }
   public E last () {
      // if empty then move elements and recall this method
      if (endStack.size() == 0) {
         this.moveFront();
      }
      // return the top end element
      return endStack.top();
   }
   public void addFirst (E e) {
      frontStack.push(e);
   }
   public void addLast (E e) {
      endStack.push(e);
   }
   public E removeFirst () {
      // if empty then move elements and recall this method
      if (frontStack.size() == 0) {
         this.moveEnd();
      }
      // return and remove the top front element
      return frontStack.pop();
   }
   public E removeLast () {
      // if empty then move elements and recall this method
      if (endStack.size() == 0) {
         this.moveFront();
      }
      // return the top end element
      return endStack.pop();
   }
   public int size () {
      return (frontStack.size() + endStack.size());
   }
   public void print() {
      E[] tmp = new E[size];
      while (size != null) {
         tmp[] = this.removeFirst();
         System.out.print(tmp);
         --size;
      }
      while (tmp.size() != null) {
         this.addFirst();
      }
   }
}
