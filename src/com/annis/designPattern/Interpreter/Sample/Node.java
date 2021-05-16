package com.annis.designPattern.Interpreter.Sample;

public abstract class Node {
    public abstract void parse(Context context) throws ParseException;
}
