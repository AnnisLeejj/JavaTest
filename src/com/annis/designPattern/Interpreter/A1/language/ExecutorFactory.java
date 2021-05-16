package com.annis.designPattern.Interpreter.A1.language;

public interface ExecutorFactory {
    public abstract Executor createExecutor(String name);
}
