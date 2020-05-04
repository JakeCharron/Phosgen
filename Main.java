import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        

        Scanner s = new Scanner(System.in);

        System.out.println("What is the Smile you want to depict? ");
        String smile = s.nextLine();

        System.out.println("What is the path you want to save the depiction? ");
        String path = s.nextLine();

        s.close();

        xyzGen gen = new xyzGen(smile, path, ".png", true);

        try {
            gen.dipiction();
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

}
