import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.modeling.builder3d.ModelBuilder3D;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import java.awt.*;
import java.io.FileWriter; 

public class Main {
    
    public static IAtom[] atoms;

    public static void main(String[] args) throws Exception {

        String smiles = "C(=O)(Cl)Cl";

        boolean return2d = false;

        dipiction(smiles, "~/Desktop/", ".png", return2d);

        System.out.println("finished");

    }


    public static void dipiction(String smiles, String path, String imgFormat, boolean is2d) throws Exception{
        if (is2d){

            IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();

            SmilesParser smipar = new SmilesParser(bldr);

            IAtomContainer mol = smipar.parseSmiles(smiles);

            mol.setTitle(smiles);

            DepictionGenerator dptgen = new DepictionGenerator();

            dptgen.withSize(200, 250).withMolTitle().withTitleColor(Color.DARK_GRAY);

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

            FileWriter writer = new FileWriter("temp.xyz");

            writer.write(smiles + "\n");
            writer.write("\n");

            for(int i = 0; i < atoms.length; i++){
                writer.write(atoms[i].getSymbol() + "\t" + atoms[i].getPoint3d().x  + "\t" + atoms[i].getPoint3d().y + "\t" + atoms[i].getPoint3d().z + "\n");
            }

            writer.close();

            
        }
    }


} 
