package com.annis.designPattern.Observer.Q1;

import com.annis.designPattern.Observer.A1.IncrementalNumberGenerator;
import com.annis.designPattern.Observer.A1.DigitObserver;
import com.annis.designPattern.Observer.A1.GraphObserver;
import com.annis.designPattern.Observer.A1.NumberGenerator;
import com.annis.designPattern.Observer.A1.Observer;

public class Main {
    public static void main(String[] args) {
        NumberGenerator generator = new IncrementalNumberGenerator(10, 50, 5);
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.execute();
    }
}
