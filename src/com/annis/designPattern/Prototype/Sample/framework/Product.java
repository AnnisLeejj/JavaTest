package com.annis.designPattern.Prototype.Sample.framework;
import java.lang.Cloneable;

public interface Product extends Cloneable {
    void use(String s);
    Product createClone();
}
