import org.openscience.cdk.CDKConstants;
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

        return2dImg("ClN(Cl)(Cl)C=O", "~/Desktop/", ".png");

        System.out.println("finished");
    }


    public static void return2dImg(String smiles, String path, String imgFormat) throws CDKException, IOException {
        IChemObjectBuilder bldr   = SilentChemObjectBuilder.getInstance();
        SmilesParser smipar = new SmilesParser(bldr);

        // Create a Smiles pareser that returns the bonds and the atoms
        IAtomContainer mol = smipar.parseSmiles(smiles);

        DepictionGenerator dptgen = new DepictionGenerator();
        // size in px (raster) or mm (vector)
        // annotations are red by default
        dptgen.withSize(200, 250)
                .withMolTitle()
                .withTitleColor(Color.DARK_GRAY);
        dptgen.depict(mol)
                .writeTo(path + smiles + imgFormat);
    }




}