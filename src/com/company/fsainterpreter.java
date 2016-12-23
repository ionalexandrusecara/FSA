package com.company;

public class fsainterpreter {


    public static void main(String[] args){
        //Process description
        FSADescriptor fsaDescriptor = new FSADescriptor();
        fsaDescriptor.processDescription(args[0]);



        //Process input
        FSAProcessor fsaProcessor = new FSAProcessor(fsaDescriptor);
        fsaProcessor.processInput();
    }
}
