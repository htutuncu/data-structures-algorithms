public class CalcStack {
    private char[] array;
    private int top;

    public CalcStack(int n){
        array = new char[n];
        top = -1;
    }
    public void push(char k) { // put item on top of stack
        array[++top] = k;
    }
    public char pop() {        // take item from top of stack
        return array[top--];
    }
    public char peek() {       // peek at top of stack
        return array[top];
    }
    public boolean isEmpty() { // true if stack is empty
        return (top <= -1);
    }

}
