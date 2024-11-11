public class TestJNI
{
    static
    {
        System.loadLibrary("native"); //load the shared library
    }

    public static void main(String[] args) {
        System.out.println("Addition is : "+ new TestJNI().add(20,40));
    }

    private native int add(int n1,int n2) ;
}
    