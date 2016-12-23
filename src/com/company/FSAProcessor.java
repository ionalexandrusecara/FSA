package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FSAProcessor {

    private static List<FSAStep> fsaSteps = new ArrayList<FSAStep>();
    private static List<String> inputSequences;
    private static List<Boolean> verdicts; //extension

    public FSAProcessor(FSADescriptor fsaDescriptor) {
        fsaSteps = fsaDescriptor.getDescription();
        inputSequences = new ArrayList<>();
        verdicts = new ArrayList<>();
        getInput();
    }

    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        String nextLine;
        while (scanner.hasNext()) {
            nextLine = scanner.nextLine();
            inputSequences.add(nextLine.replace(" ", ""));
        }
    }

    public int checkDuplicate(String str, int k) { //extension
        for (int i = 0; i < k; i++) {
            if (inputSequences.get(i).equals(str)) {
                return i;
            }
        }
        return -1;
    }


    public void processInput() {
        char character;
        int nextState;
        boolean flag;
        int k;
        for (int i = 0; i < inputSequences.size(); i++) {
            nextState = 1;
            flag = false;
            k = checkDuplicate(inputSequences.get(i), i);
            if (k != -1) { //extension
                if (verdicts.get(k) == true) {
                    System.out.println("Accepted");
                } else {
                    System.out.println("Not accepted");
                }
            } else {
                for (int j = 0; j < inputSequences.get(i).length(); j++) {
                    character = inputSequences.get(i).charAt(j);
                    if (checkTransition(character, nextState)) {
                        nextState = makeTransition(nextState, character);

                        if (hasAcceptingState(nextState, i, j)) {
                            System.out.println("Accepted");
                            verdicts.add(true);
                            flag = true;
                        }
                    } else {
                        j = inputSequences.get(i).length() + 1;
                    }
                }
                if (flag == false) {
                    System.out.println("Not accepted");
                    verdicts.add(false);
                }
            }
        }

    }

    public boolean checkTransition(char character, int nextState) {
        for (int i = 0; i < fsaSteps.size(); i++) {
            if (nextState == fsaSteps.get(i).getInputState()) {
                if (character == fsaSteps.get(i).getCharacter()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int makeTransition(int nextState, char character) {
        for (int i = 0; i < fsaSteps.size(); i++) {
            if (nextState == fsaSteps.get(i).getInputState()) {
                if (character == fsaSteps.get(i).getCharacter()) {
                    nextState = fsaSteps.get(i).getOutputState();
                }
            }
        }
        return nextState;
    }

    private boolean hasAcceptingState(int nextState, int i, int j) {
        for (int k = 0; k < fsaSteps.size(); k++) {
            if (fsaSteps.get(k).isAccepting()) {
                if ((nextState == fsaSteps.get(k).getOutputState()) && ((inputSequences.get(i).length()) - 1 == j)) {
                    return true;
                }
            }
        }
        return false;
    }

}
