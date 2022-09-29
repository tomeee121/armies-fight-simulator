package org.example.fight.methods;

import java.util.*;


/*
SORTING HASHMAP TO LINKEDHASHMAP BY VALUES
*/
public class test {

    public test(String message) {System.out.println("outer "+message);}
    class Inner { public Inner(String message) {

            System.out.println("inner "+message);}}

    int uu;

    public LinkedHashMap<Integer, String> sortHashMapByValues(

            HashMap<Integer, String> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<String> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, String> sortedMap =
                new LinkedHashMap<>();

        Iterator<String> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            String val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                String comp1 = passedMap.get(key);
                String comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public static void main(String[] args) {

        yy yyu = new yy();
        yy.main3();


        Appl.main();

        Student jj = new Student("ll");
        jj.name = "ooo";
        System.out.println(jj.name);
//        Student doo = new Student();
//        doo.getName();

        //Albo pętla for i usuwasz przez list.remove(object/index) albo while(iterator.hasNext()) i iterator,remove;

//        Set<Student> objects = new HashSet<>();
//        objects.add(new Student("Ivan"));
//        objects.add(new Student("Petro"));

//        Set<Student> objects2 = new TreeSet<>();
//        objects2.add(new Student("Ivan"));
//        objects2.add(new Student("Petro"));
//        objects2.add(new Student("Ivan"));
//        System.out.println(objects2);

//        Map<String, String> objects = new HashMap<>();
//        objects.put("key1","Ivan");
//        objects.put("key2","Elo");
//
//        Map<String, String> objects2 = new TreeMap<>();
//        objects2.put("key1","Ivan");
//        objects2.put("key2","Elo");
//        System.out.println(objects.equals(objects2));

//        Set<String> objects = new HashSet<>();
//        objects.add("Ivan");
//        objects.add("Elo");
//
//        Set<String> objects2 = new TreeSet<>();
//        objects2.add("Ivan");
//        objects2.add("Elo");
//        System.out.println(objects.equals(objects2));

//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("3");
//
//        for (String value : list) {
//            if(value.equals("3")) {
//                list.remove(value);
//        }}
//        System.out.println(list);
//
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("3");
//        for (String current : list) {
//            if(current.contains("3")) {
//                list.remove(current);
//            }
//        }
//        System.out.println(list);


//        Set<Object> objects = new TreeSet<>();
//        objects.add(null);
//        System.out.println(objects);


//        HashSet<Object> objects = new HashSet<>();
//        objects.add(null);
//        objects.add(null);
//        objects.add(null);
//        System.out.println(objects);


//        HashSet<Object> objects = new HashSet<>();
//        objects.add(new Student("Ivan"));
//        objects.add(new Student("Robert"));
//        objects.add(new Student("Ivan"));
//        System.out.println(objects);



//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
////        Integer[] arr = new Integer[4];
//        Object[] arr = list.toArray();
//        list.add(0,5);
//        arr[0] = 5;
//        System.out.println(Arrays.toString(arr));





//        Integer[] arr = {1,2,3,4};
//        List<Integer> list = Arrays.asList(arr);
//        List<Integer> integers = new ArrayList<>();
//        integers.addAll(list);
//        integers.add(0, 5);
//        System.out.println(integers);

    }
}

interface I { }
class A implements I{

    protected int i;

    public A() {
    }
    public A(int i) {
        this.i = i;
    }
    void m2(){
        System.out.println("A");
    }

}
class B extends A{
//    void m2(){
//        System.out.println("B");
//    }
}
class C extends B{ }

class yy {
    public static void main3(String... args) {
        A a = new A();
        B b = new B();
        a=(A)b;
        a = b;
//        a = I(b);
//        I i = (C)a;
        b = (B)a;
//        b = a;

        A asg= new B();
        asg.m2();

    }
}

class Client {
    B SomeMethod() {return new B();}
}

class SpecificClient extends Client {
    public B SomeMethod() {return new B();}
        }


    class Appl{
    static void m1(A a){
        System.out.println("                A");
    }
    static void m1 (B b) {
        System.out.println("          B");
    }

        public static void main() {
            m1(null);
        }
    }
