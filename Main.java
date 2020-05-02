public class Main {

    public static void main(String[] args) throws Exception {

        String smiles = "C(=O)(Cl)Cl";
        String path = "~/Desktop/";
        String format = ".png";
        boolean return2d = true;

        // dipiction(smiles, path, format, false);

        xyzGen gen = new xyzGen(smiles, path, format, return2d);

        gen.dipiction();

        System.out.println("finished");

    }

} 
