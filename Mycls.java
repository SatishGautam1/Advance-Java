public class Mycls {
    public static void main(String[] args) {
        System.out.println("Our 1st Advance Java class.");
        System.out.println("");
        System.out.println("********For Loop************");
        // For Loop
        Mycls clsobj = new Mycls();
        clsobj.displayLoop();
        System.out.println("********While Loop************");
        // While Loop
        Mycls clsobj2 = new Mycls();
        clsobj2.displayWhileLoop();
        System.out.println("********Do While Loop************");
         // Do While Loop
        Mycls clsobj3 = new Mycls();
        clsobj3.displayWhileLoop();
        System.out.println("********For Each Loop************");
          // For Each Loop
        Mycls clsobj4 = new Mycls();
        clsobj4.displayWhileLoop();
    }

    public void displayLoop() {
        // For Loop
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public void displayWhileLoop() {
        // While Loop
        int i = 0;
        while (i < 10) {
            System.out.println(i);
            i++;
        }
    }

    public void displayDoWhileLoop() {
        // Do While Loop
        int i = 0;
        do while (i < 10) {
            System.out.println(i);
            i++;
        } while(i<10);
    }

    public void displayForEachLoop() {
        // For Each Loop While Loop
        int [] marks = {1, 7, 10, 9, 4, 3};
        for(int x:marks){
            System.out.println(x);
        }
    } 
}

