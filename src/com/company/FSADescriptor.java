package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FSADescriptor {
    private static List<FSAStep> fsaSteps = new ArrayList<FSAStep>();

    private static String descriptionFile;

    public void processDescription(String descriptionFile) {
        this.descriptionFile = descriptionFile;
        int inputState;
        char character;
        int outputState;
        boolean acceptingState;
        String line;
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(descriptionFile)));
            String[] details;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                acceptingState = false;
                details = line.split(" ");
                //System.out.println(details[0]);
                inputState = Integer.parseInt(details[0]);
                character = details[1].charAt(0);
                outputState = Integer.parseInt(details[2]);
                if (details.length == 4) {
                    acceptingState = true;
                }
                fsaSteps.add(new FSAStep(inputState, character, outputState, acceptingState));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    public List<FSAStep> getDescription() {
        return fsaSteps;
    }

}
