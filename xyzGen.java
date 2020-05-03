import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.modeling.builder3d.ModelBuilder3D;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import java.io.FileWriter; 
import java.awt.*;

// sample use
//         String smiles = "C(=O)(Cl)Cl";
//         String path = "~/Desktop/";
//         String format = ".png";
//         boolean return2d = true;

//         // dipiction(smiles, path, format, false);

//         xyzGen gen = new xyzGen(smiles, path, format, return2d);

//         gen.dipiction();
public class xyzGen {

    public String smiles;

    public String path;
    
    public String imgFormat;

    public boolean is2d;

    public static IAtom[] atoms;


    public xyzGen(String Smile, String path, String format, boolean return2d){
        smiles = Smile;
        this.path = path;
        imgFormat = format;
        is2d = return2d;
    }

    public void dipiction() throws Exception{
        if (is2d){

            IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();

            SmilesParser smipar = new SmilesParser(bldr);

            IAtomContainer mol = smipar.parseSmiles(smiles);

            mol.setTitle(smiles);

            DepictionGenerator dptgen = new DepictionGenerator();

            dptgen.withSize(200, 250).withMolTitle().withTitleColor(Color.DARK_GRAY);
            System.out.println(path + mol.getTitle() + imgFormat);
            dptgen.depict(mol).writeTo(path + mol.getTitle() + imgFormat);
            
        } else {

            IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();

            SmilesParser smipar = new SmilesParser(bldr);

            IAtomContainer mol = smipar.parseSmiles(smiles);

            ModelBuilder3D mb3d = ModelBuilder3D.getInstance(bldr);

            IAtomContainer coords = mb3d.generate3DCoordinates(mol, false);

            try{
                int totalAtoms = 0;
                for (IAtom atom : coords.atoms()) {
                    totalAtoms++;
                }

                // init atom array
                atoms = new IAtom[totalAtoms];

                // creating atom array
                int curAtomIndex = 0;
                for (IAtom atom : coords.atoms()) {
                    atoms[curAtomIndex] = atom;
                    curAtomIndex++;
                }

            } catch (Exception e) {}

            FileWriter writer = new FileWriter("mol.xyz");

            writer.write(smiles + "\n");
            writer.write("\n");

            for(int i = 0; i < atoms.length; i++){
                writer.write(atoms[i].getSymbol() + "\t" + atoms[i].getPoint3d().x  + "\t" + atoms[i].getPoint3d().y + "\t" + atoms[i].getPoint3d().z + "\n");
            }

            writer.close();

            
        }
    }


}