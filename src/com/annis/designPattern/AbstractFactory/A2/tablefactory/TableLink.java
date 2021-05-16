package com.annis.designPattern.AbstractFactory.A2.tablefactory;
import com.annis.designPattern.AbstractFactory.A2.factory.Link;

public class TableLink extends Link {
    public TableLink(String caption, String url) {
        super(caption, url);
    }
    public String makeHTML() {
        return "<td><a href=\"" + url + "\">" + caption + "</a></td>\n";
    }
}
