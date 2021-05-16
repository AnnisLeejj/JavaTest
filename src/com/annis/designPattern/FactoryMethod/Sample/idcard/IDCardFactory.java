package com.annis.designPattern.FactoryMethod.Sample.idcard;
import com.annis.designPattern.FactoryMethod.Sample.framework.Factory;
import com.annis.designPattern.FactoryMethod.Sample.framework.Product;
import java.util.*;

public class IDCardFactory extends Factory {
    private List owners = new ArrayList();
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }
    protected void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
    }
    public List getOwners() {
        return owners;
    }
}
