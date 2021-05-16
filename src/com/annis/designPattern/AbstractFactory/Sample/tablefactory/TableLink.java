package com.annis.designPattern.AbstractFactory.Sample.tablefactory;
import com.annis.designPattern.AbstractFactory.Sample.factory.*;

public class TableLink extends Link {
    public TableLink(String caption, String url) {
        super(caption, url);
    }
    public String makeHTML() {
        return "<td><a href=\"" + url + "\">" + caption + "</a></td>\n";
    }
}
