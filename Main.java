// import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        
        String smiles = "C(=O)(Cl)Cl";

        boolean return2d = true;

        IChemObjectBuilder bldr   = SilentChemObjectBuilder.getInstance();

        SmilesParser smipar = new SmilesParser(bldr);

        IAtomContainer mol = smipar.parseSmiles(smiles);


        mol.setTitle(smiles);
        if(return2d){
            return2dImg("~/Desktop/", ".png", mol);
        }else{
            // return3d
        }

        System.out.println("finished");

    }


    public static void return2dImg(String path, String imgFormat, IAtomContainer molecule) throws CDKException, IOException {
        
        DepictionGenerator dptgen = new DepictionGenerator();

        dptgen.withSize(200, 250)
                .withMolTitle()
                .withTitleColor(Color.DARK_GRAY);

        dptgen.depict(molecule)
                .writeTo(path + molecule.getTitle() + imgFormat);

    }

    // public static void return3dImg(String smiles, String path, String imgFormat){



    
}
