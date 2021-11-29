package amd.example.java.bean;

import androidx.annotation.NonNull;

/**
 * @author Created by on LvJP 2021-11-9 , 0009
 */
public class CloneTest implements Cloneable{

    private String name;
    private int age;

    public CloneTest() {
    }

    public CloneTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CloneTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @NonNull
    @Override
    public Object clone()  {
        CloneTest test = null;
        try {
            test = (CloneTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return test;
    }
}
