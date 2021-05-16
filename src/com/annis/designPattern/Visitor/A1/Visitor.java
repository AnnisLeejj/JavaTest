package com.annis.designPattern.Visitor.A1;

public abstract class Visitor {
    public abstract void visit(File file);
    public abstract void visit(Directory directory);
}
