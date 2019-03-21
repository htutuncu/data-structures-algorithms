public class MyStack {

    public static class Coordinate{
        int x;
        int y;
        public Coordinate(int i,int j){this.x = i; this.y = j;}
        public int getX(){ return x;}
        public int getY() { return y; }
    }

    private int size;
    private MyStack.Coordinate[] arr;
    private int top;

    public MyStack(){
        size = 100;
        arr = new MyStack.Coordinate[size];
        top = -1;
    }
    public void push(Coordinate num){

        arr[++top] = num;

    }
    public Coordinate pop(){
        int a = top;
        if ( top<0 ) {
            System.out.println("Stack is empty." + "top=" + top);
            Coordinate nul = new Coordinate(0,0);
            return nul;
        }
        else
            return arr[top--];


    }
    public Coordinate peek(){
        return arr[top];
    }
    public boolean IsEmpty(){
        return (top<0);
    }


}
