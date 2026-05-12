package TestingSelenium;

interface A{
   default void show(){
       System.out.println("I am from Interface A");
   }
}
interface B {
    default void show(){
        System.out.println("I am from interface B");
    }
}
abstract class D{

    abstract void view();
}
class C implements A,B{

    @Override
    public void show() {
        B.super.show();
    }

}
public class Main {
    public static void main(String[] args) {
        A c = new C();
        c.show();

    }
}