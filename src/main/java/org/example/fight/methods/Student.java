package org.example.fight.methods;

import java.util.Objects;

public class Student  /* implements Comparable<Student> */{

    public static void main(String[] args) {
        test test_out = new test("test out");
//        test.Inner sd = new test.Inner("sd");
    }

    protected String name = "oooooo";

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

//    @Override
//    public int compareTo(Student o) {
//        return name.compareTo(o.name);
//    }
}
