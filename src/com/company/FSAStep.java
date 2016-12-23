package com.company;


public class FSAStep {
    private int inputState;
    private char character;
    private int outputState;
    private boolean accept;

    public FSAStep(int inputState, char character, int outputState, boolean accept) {
        this.inputState = inputState;
        this.character = character;
        this.outputState = outputState;
        this.accept = accept;
    }

    public int getInputState(){
        return inputState;
    }

    public char getCharacter(){
        return character;
    }

    public int getOutputState(){
        return outputState;
    }

    public boolean isAccepting(){
        return accept;
    }



}
