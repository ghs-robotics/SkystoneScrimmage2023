package org.firstinspires.ftc.teamcode.constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWrite {

    File autoI;
    File hsvC;

    private static Scanner autoInstructions;
    private static Scanner hsvConstants;

    private final String directory = "/";

    public ReadWrite(){
        hsvC = new File(directory + "HSVConstants.txt");
        autoI = new File(directory + "AutoInstruction.txt");

        try {
            hsvConstants = new Scanner(hsvC);
            autoInstructions = new Scanner(autoI);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       CV CONSTANTS                                         //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Integer> getHSV(){
        ArrayList<Integer> hsv = new ArrayList<Integer>();
        while (hsvConstants.hasNextInt())
            hsv.add(hsvConstants.nextInt());

        return hsv;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          AUTO                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Integer> getAutoInstructions(){
        ArrayList<Integer> auto = new ArrayList<Integer>();
        while (autoInstructions.hasNextInt())
            auto.add(autoInstructions.nextInt());

        return auto;
    }


    // TODO write a method that will auto delete the top line of the auto file when called
    public static void stepExecuted(){

    }
}
