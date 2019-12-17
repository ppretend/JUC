public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo = new Foo(){

            @Override
            public void sayHello() {
                System.out.println("****************hello");
            }
        };

        foo.sayHello();*/

//        Foo foo2 = () -> {System.out.println("****************hello");};
//        foo2.sayHello();

        Foo foo2 = (x , y ) -> {
            System.out.println(x*y);
        };
        foo2.add(10,2);
        System.out.println(foo2.div(12,6));
        System.out.println(Foo.mul(14,3));
    }
}

@FunctionalInterface
interface Foo{
    //public void sayHello();

    public void add(int x , int y );

    default int div(int x , int y){
        return x/y;
    }

    default int div2(int x , int y){
        return x/y;
    }

    public  static int mul(int x , int y ){
        return x * y ;
    }
}
