package com.annis;

import java.io.*;

public class ObjectSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        write();
        read();
    }

    public static void read() throws IOException, ClassNotFoundException {
        File file = new File("object.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        Person person = (Person) object;
        System.out.println(person.name +"    "+person.address);
    }
    public static void write() throws IOException {
        File file = new File("object.txt");
        System.out.println(file.getAbsolutePath());
        FileOutputStream fs = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fs);
        Person person = new Person("name_ljj", "address_ljj");
        objectOutputStream.writeObject(person);
        fs.close();
        objectOutputStream.close();
    }
}

class Person implements Serializable {

        String name;
        String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
