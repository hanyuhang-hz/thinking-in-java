package thinkinjava.hyh;

import java.io.*;
import java.lang.reflect.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ThinkInJavaTest {
    public static void main() {
        System.out.println("ThinkInJavaTest>>>");
        PassObject.chapter3_4();
        Equivalence.chapter3_7();
        ArrayNew.chapter5_8();
        NewVarArgs.chapter5_8();
        Music.chapter8_2();
        PrivateOverride.chapter8_2();
        PolyConstructors.chapter8_3();
        Apply.chapter9_3();
        Factories.chapter9_9();
        Parcel7.chapter10_6();
        Parcel8.chapter10_6();
        Callbacks.chapter10_8();
        PrintingContainers.chapter11_4();
        QueueDemo.chapter11_11();
        PriorityQueueDemo.chapter11_11();
        ModifyingArraysAsList.chapter11_13();
        Immutable.chapter13_1();
        UsingStringBuilder.chapter13_2();
        Shapes.chapter14_1();
        ShowMethods.chapter14_6();
        SimpleProxyDemo.chapter14_7();
        SimpleDynamicProxy.chapter14_7();
        ArrayOptions.chapter16_2();
        MultiArray.chapter16_4();
        CopyingArrays.chapter16_7();
        StringSorting.chapter16_7();
        CollectionMethods.chapter17_3();
        Unsupported.chapter17_4();
        Lists.chapter17_5();
        SortedSetDemo.chapter17_6();
        QueueBehavior.chapter17_7();
        ToDoList.chapter17_7();
        Maps.chapter17_8();
        CountedString.chapter17_9();
        Utilities.chapter17_11();
        //FailFast.chapter17_11();
        DirList.chapter18_1();
        BufferedInputFile.chapter18_6();
        try {
            MemoryInput.chapter18_6();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BasicFileOutput.chapter18_6();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            StoringAndRecoveringData.chapter18_6();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextFile.chapter18_7();
        /*
        try {
            Redirecting.chapter18_8();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        try {
            GetChannel.chapter18_10();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ChannelCopy.chapter18_10();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferToText.chapter18_10();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GetData.chapter18_10();
        IntBufferDemo.chapter18_10();
        ViewBuffers.chapter18_10();
        UsingBuffers.chapter18_10();
        try {
            FileLocking.chapter18_10();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EnumClass.chapter19_1();
        Reflection.chapter19_4();
        ConstantSpecificMethod.chapter19_10();
        //todo
        MainThread.chapter21_2();
    }
}

class PassObject {
    static class Letter {
        char c;
    };
    static void f(Letter y) {
        y.c = 'z';
    }
    public static void chapter3_4() {
        System.out.println("PassObject chapter3_4>>>");
        Letter x = new Letter();
        x.c = 'y';
        f(x);
        System.out.println(x.c);
    }
};

class Equivalence {
    public static void chapter3_7() {
        Integer integer1 = new Integer(7);
        Integer integer2 = new Integer(7);
        System.out.println("integer1==integer2:" + (integer1==integer2));
        System.out.println("integer1.equals(integer2):" + integer1.equals(integer2));
    }
};

class ArrayNew {
    public static void chapter5_8() {
        int[] a = new int[10];
        Integer[] b = new Integer[10];

        Random random = new Random(7);
        for(int i=0;i<10;i++) {
            a[i] = random.nextInt(10);
            b[i] = random.nextInt(20);
        }

        System.out.println("a.length:" + a.length);
        System.out.println(Arrays.toString(a));
        System.out.println("b.length:" + b.length);
        System.out.println(Arrays.toString(b));
    }
};

class NewVarArgs {
    static class A {}
    static void printObject(Object... args) {
        for(Object object:args) {
            System.out.println("object:" + object);
        }
    }
    public static void chapter5_8() {
        printObject(new Integer(7),3.14F,new A());
    }
};

class Instrument {
    void play() {
        System.out.println("Instrument!");
    }
};
class Wind extends Instrument {
    void play() {
        System.out.println("Wind!");
    }
};
class Percussion extends Instrument {
    void play() {
        System.out.println("Percussion!");
    }
};
class Music {
    static void tune(Instrument i) {
        i.play();
    }
    public static void chapter8_2() {
        Wind wind = new Wind();
        Percussion percussion = new Percussion();
        tune(wind);
        tune(percussion);
    }
};

class PrivateOverride {
    /*
    public void f() {
        System.out.println("PrivateOverride!");
    }
    */
    private void f() {
        System.out.println("PrivateOverride!");
    }
    public static void chapter8_2() {
        PrivateOverride privateOverride = new Derived();
        privateOverride.f();
        ((Derived)privateOverride).f();
    }
};
class Derived extends PrivateOverride {
    public void f() {
        System.out.println("Derived PrivateOverride!");
    }
}

class Glyph {
    Glyph() {
        System.out.println("Glyph before draw!");
        draw();
        System.out.println("Glyph after draw!");
    }
    void draw() {
        System.out.println("Glyph draw()!");
    }
};
class RoundDraw extends Glyph {
    RoundDraw(int r) {
        radius = r;
        System.out.println("RoundDraw draw()! radius:" + radius);
    }
    private int radius = 1;
    void draw() {
        System.out.println("RoundDraw draw()! radius:" + radius);
    }
};
class PolyConstructors {
    public static void chapter8_3() {
        new RoundDraw(5);
    }
};

interface Processor {
    String name();
    String process(String input);
};
class Upcase implements Processor {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }
    @Override
    public String process(String input) {
        return input.toLowerCase();
    }
};
class Downcase implements Processor {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }
    @Override
    public String process(String input) {
        return input.toUpperCase();
    }
};
class Apply {
    static void process(Processor processor, String input) {
        System.out.println(processor.process(input));
    }
    public static void chapter9_3() {
        String input = "Hyh";
        process(new Upcase(), input);
        process(new Downcase(), input);
    }
};

interface Service {
    void method();
};
class Implementation1 implements Service {
    @Override
    public void method() {
        System.out.println("Implementation1 Service!");
    }
};
class Implementation2 implements Service {
    @Override
    public void method() {
        System.out.println("Implementation2 Service!");
    }
};
interface ServiceFactory {
    Service getService();
};
class ImplementationFactory1 implements ServiceFactory {
    @Override
    public Service getService() {
        return new Implementation1();
    }
};
class ImplementationFactory2 implements ServiceFactory {
    @Override
    public Service getService() {
        return new Implementation2();
    }
};
class Factories {
    public static void serviceConsumer(ServiceFactory serviceFactory) {
        Service service = serviceFactory.getService();
        service.method();
    }
    public static void chapter9_9() {
        serviceConsumer(new ImplementationFactory1());
        serviceConsumer(new ImplementationFactory2());
    };
};

interface Contents {
    int value();
}
class Parcel7 {
    public Contents contents() {
        //(1)匿名内部类是Contents的子类
        return new Contents() {
            @Override
            public int value() {
                System.out.println("Contents value!");
                return 0;
            }
        };
    }
    public static void chapter10_6() {
        Parcel7 parcel7 = new Parcel7();
        //(2)子类向上转型
        Contents contents = parcel7.contents();
        //(3)多态，运行时加载
        contents.value();
    }
};

class Wrapping {
    private int i;
    public Wrapping(int x) {
        i = x;
    }
    public int value() {
        System.out.println("Wrapping i:" + i);
        return i;
    }
}
class Parcel8 {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            public int value() {
                return super.value();
            }
        };
    }
    public static void chapter10_6() {
        Parcel8 parcel8 = new Parcel8();
        Wrapping wrapping = parcel8.wrapping(7);
        wrapping.value();
    }
}

interface Incrementable {
    void increment();
}
//(1)实现接口很简单
class Callee1 implements Incrementable {
    private int i = 0;
    @Override
    public void increment() {
        i++;
        System.out.println("Callee1 i:" + i);
    }
}
class MyIncrementable {
    public void increment() {
        System.out.println("MyIncrementable");
    }
}
//(2)如果你的类必须以其他方式实现increment(),必须使用内部类
class Callee2 extends MyIncrementable {
    private int i = 7;
    public void increment() {
        super.increment();
        i+=3;
        System.out.println("Callee2 i:" + i);
    }
    //匿名内部类
    Incrementable callback() {
        return new Incrementable() {
            @Override
            public void increment() {
                Callee2.this.increment();
            }
        };
    }
}
class Caller {
    private Incrementable callback;
    Caller(Incrementable call) {
        callback = call;
    }
    void run() {
        callback.increment();
    }
}
class Callbacks {
    public static void chapter10_8() {
        Callee1 callee1 = new Callee1();
        Caller caller1 = new Caller(callee1);
        caller1.run();

        Callee2 callee2 = new Callee2();
        Caller caller2 = new Caller(callee2.callback());
        caller2.run();
    }
}

class PrintingContainers {
    static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }
    static Map fill(Map<String,String> map) {
        map.put("rat","1");
        map.put("cat","2");
        map.put("dog","3");
        map.put("dog","4");
        return map;
    }
    public static void chapter11_4() {
        //Collection
        System.out.println(fill(new ArrayList<>()));
        System.out.println(fill(new LinkedList<>()));
        System.out.println(fill(new HashSet<>()));              //fastest
        System.out.println(fill(new TreeSet<>()));              //比较的顺序
        System.out.println(fill(new LinkedHashSet<>()));        //被添加的顺序
        //Map
        System.out.println(fill(new HashMap<>()));              //fastest
        System.out.println(fill(new TreeMap<>()));              //比较的顺序
        System.out.println(fill(new LinkedHashMap<>()));        //被添加的顺序
    }
}

class QueueDemo {
    static void printQ(Queue queue) {
        while(queue.peek() != null) {
            System.out.print( " " + queue.remove());
        }
        System.out.println();
    }
    static void chapter11_11() {
        Queue<Integer> queue = new LinkedList<>();
        Random random = new Random(7);
        for(int i=0;i<10;i++) {
            queue.offer(random.nextInt(10));
        }
        printQ(queue);
    }
}

class PriorityQueueDemo {
    static void chapter11_11() {
        List<Integer> list = Arrays.asList(7,17,27,37,40);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(list);
        QueueDemo.printQ(priorityQueue);

        priorityQueue = new PriorityQueue<>(list.size(),Collections.reverseOrder());
        priorityQueue.addAll(list);
        QueueDemo.printQ(priorityQueue);
    }
}

class ModifyingArraysAsList {
    static void chapter11_13() {
        Random random = new Random(7);
        Integer[] ia = {1, 2, 3, 4, 5, 6, 7};

        List<Integer> list1 = new ArrayList<>(Arrays.asList(ia));
        System.out.println("before:" + list1);
        Collections.shuffle(list1,random);
        System.out.println("after:" + list1);
        System.out.println("Arrays.toString(ia):" + Arrays.toString(ia));
        System.out.println("ia:" + ia);

        List<Integer> list2 = Arrays.asList(ia);
        System.out.println("before:" + list2);
        Collections.shuffle(list2,random);
        System.out.println("after:" + list2);
        System.out.println("Arrays.toString(ia):" + Arrays.toString(ia));
        System.out.println("ia:" + ia);
    }
}

class Immutable {
    public static String upcase(String s) {
        return s.toUpperCase();
    }
    public static void test(String s) {
        s = "test";
        System.out.println("test:" + s);
    }
    public static void chapter13_1() {
        String q = "how";
        String qq = upcase(q);
        System.out.println(q);
        System.out.println(qq);

        test(q);
        System.out.println(q);
    }
}

class UsingStringBuilder {
    public String toString() {
        Random random = new Random(7);
        StringBuilder res = new StringBuilder("[");
        for (int i=0;i<5;i++) {
            res.append(random.nextInt(10));
            res.append(", ");
        }
        //[...)，即删除res.length()-2和res.length()-1位置的字符
        res.delete(res.length()-2,res.length());
        res.append("]");

        return res.toString();
    }
    public static void chapter13_2() {
        UsingStringBuilder usingStringBuilder = new UsingStringBuilder();
        System.out.println(usingStringBuilder.toString());
    }
}

abstract class Shape {
    void draw() {
        System.out.println(this + ".draw()!");
    }
    abstract public String toString();
}
class Circle extends Shape{
    @Override
    public String toString() {
        return "Circle";
    }
}
class Square extends Shape{
    @Override
    public String toString() {
        return "Square";
    }
};
class Shapes {
    public static void chapter14_1() {
        List<Shape> shapeList = Arrays.asList(new Circle(),new Square());   //(1)upcast
        for(Shape s:shapeList) {        //(2)RTTI Object->Shape
            s.draw();                   //(3)polymorphic
        }
    }
}

class ShowMethods {
    public ShowMethods() {
        System.out.println("test");
    }
    public static void chapter14_6() {
        try {
            Class<?> c = Class.forName("thinkinjava.hyh.ShowMethods");
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            System.out.println("methods:");
            for(Method method:methods) {
                System.out.println(method.toString());
            }

            System.out.println("ctors:");
            for(Constructor constructor:ctors) {
                System.out.println(constructor.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

interface Interface {
    void doSomething();
}
class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }
}
class SimpleProxy implements Interface {
    private Interface proxy;
    public SimpleProxy(Interface inter) {
        proxy = inter;
    }
    @Override
    public void doSomething() {
        System.out.println("SimpleProxy:");
        proxy.doSomething();
    }
}
class SimpleProxyDemo{
    public static void consumer(Interface inter) {
        inter.doSomething();
    }
    public static void chapter14_7() {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object real;
    public DynamicProxyHandler(Object real) {
        this.real = real;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:" + proxy.getClass());
        System.out.println("method:" + method);
        System.out.println("args:" + args);
        if(args != null) {
           for(Object arg:args) {
               System.out.println("arg:" + arg);
           }
        }
        return method.invoke(real,args);
    }
}
class SimpleDynamicProxy {
    public static void consumer(Interface inter){
        inter.doSomething();
    }
    public static void chapter14_7() {
        RealObject real = new RealObject();
        consumer(real);

        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(real));
        consumer(proxy);
    }
}

class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    public String toString() {
        return "Sphere:" + id;
    }
}
class ArrayOptions {
    public static void chapter16_2() {
        //(1)对象数组
        //(i)默认初始化
        BerylliumSphere[] a = new BerylliumSphere[3];
        System.out.println(Arrays.toString(a));
        //(ii)列表初始化
        BerylliumSphere[] b = new BerylliumSphere[] {new BerylliumSphere(),new BerylliumSphere()};
        System.out.println(Arrays.toString(b));

        //(2)基本数组
        //(i)默认初始化
        int[] c = new int[5];
        System.out.println(Arrays.toString(c));
        //(ii)列表初始化
        int[] d = new int[5];
        for(int i=0;i<5;i++){
            d[i] = 7;
        }
        System.out.println(Arrays.toString(d));
    }
}

class MultiArray {
    public static void chapter16_4() {
        Integer[][] a = new Integer[3][];
        for(int i=0;i<a.length;i++) {
            a[i] = new Integer[5];
            for(int j=0;j<a[i].length;j++) {
                a[i][j] = i+j;
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
}

class CopyingArrays {
    public static void chapter16_7() {
        int[] src = new int[] {1,2,3,4,5};
        int[] dst = new int[10];
        System.arraycopy(src,0,dst,0,src.length);
        System.out.println(Arrays.toString(dst));

        Integer[] a = new Integer[5];
        Arrays.fill(a,7);
        Integer[] b = new Integer[10];
        System.arraycopy(a,0,b,5,a.length);
        System.out.println(Arrays.toString(b));
        a[4] = 11;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}

class StringSorting {
    public static void chapter16_7() {
        String[] str = new String[]{"Abcde","AAAAb","HxxHv","aaaaa"};
        System.out.println(Arrays.toString(str));

        Arrays.sort(str);
        System.out.println(Arrays.toString(str));
        int location = Arrays.binarySearch(str,"AAAAb");
        System.out.println("location:" + location);

        Arrays.sort(str,Collections.reverseOrder());
        System.out.println(Arrays.toString(str));
        //wrong
        //location = Arrays.binarySearch(str,"AAAAb");
        location = Arrays.binarySearch(str,"AAAAb", Collections.reverseOrder());
        System.out.println("location:" + location);

        Arrays.sort(str,String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(str));
        location = Arrays.binarySearch(str,"AAAAb", String.CASE_INSENSITIVE_ORDER);
        System.out.println("location:" + location);
    }
}

class CollectionMethods {
    public static void chapter17_3() {
        Collection<String> c = new ArrayList<>();
        c.add("ten");
        c.add("tens");
        System.out.println(c.toString());

        Object[] arr = c.toArray();
        System.out.println(arr.toString());
        for(int i=0;i< arr.length;i++){
            System.out.println(arr[i].toString());
        }
        String[] str = c.toArray(new String[0]);
        for(int i=0;i< arr.length;i++){
            System.out.println(str[i]);
        }

        c.clear();
        System.out.println(c.toString());
    }
}

class Unsupported {
    static void test(String msg, List<String> list) {
        Collection<String> c = list;
        Collection<String> subList = list.subList(1,8); //[1,8)
        Collection<String> c2 = new ArrayList<>(subList);
        System.out.println("msg:" + msg);
        System.out.println(c.toString());

        try {
            c.retainAll(c2);
            System.out.println(c.toString());
        }catch (Exception e) {
            System.out.println("retainAll:" + e);
        }

        try {
            c.removeAll(c2);
            System.out.println(c.toString());
        }catch (Exception e) {
            System.out.println("removeAll:" + e);
        }

        try {
            c.add("A");
            c.add("C");
            System.out.println(c.toString());
        }catch (Exception e) {
            System.out.println("add:" + e);
        }

        try {
            c.remove("A");
            System.out.println(c.toString());
        }catch (Exception e) {
            System.out.println("remove:" + e);
        }

        try {
            list.set(0,"Z");
            System.out.println(list.toString());
        }catch (Exception e) {
            System.out.println("list.set:" + e);
        }
    }
    public static void chapter17_4() {
        List<String> list = Arrays.asList("A B C D E F G H I J".split(" "));
        test("Modifiable", new ArrayList<>(list));
        test("Arrays.asList", list);
        test("Unmodifiable", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}

class Lists {
    static void basicTest(List<String> list) {
        System.out.println(list);
        list.add(1,"a");
        list.add("hyh");
        List<String> ls = Arrays.asList("ab cd".split(" "));
        list.addAll(ls);
        System.out.println(list);
        String s = list.get(2);
        int index = s.indexOf("hyh");
        System.out.println("s:" + s + " index:" + index);
    }
    static void iterMotion(List<String> list) {
        ListIterator<String> iter = list.listIterator();
        Boolean b = iter.hasNext();
        String s = iter.next();
        Integer i = iter.nextIndex();
        System.out.println("b:" + b + " s:" + s + " i:" + i);
    }
    static void iterManipulation(List<String> list) {
        ListIterator<String> iter = list.listIterator();
        iter.add("7");
        System.out.println(list);
        //删除"7"下一个元素
        iter.next();
        iter.remove();
        System.out.println(list);
        //替换"7"下一个元素
        iter.next();
        iter.set("xjy");
        System.out.println(list);
    }
    public static void chapter17_5() {
        List<String> list = Arrays.asList("a b c d e f".split(" "));
        //修改容器大小的操作不可直接传入Arrays.asList固定容器
        basicTest(new LinkedList<>(list));
        iterMotion(list);
        //修改容器大小的操作不可直接传入Arrays.asList固定容器
        iterManipulation(new LinkedList<>(list));
    }
}

class SortedSetDemo {
    public static void chapter17_6() {
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet, "one two three four five".split(" "));
        System.out.println(sortedSet);
        System.out.println(sortedSet.first());
        System.out.println(sortedSet.last());
        String low = null,high = null;
        Iterator<String> iter = sortedSet.iterator();
        for(int i=0;i<sortedSet.size();i++) {
            String str = iter.next();
            if(i==1) {
                low = str;
            }
            if(i==sortedSet.size()-1) {
                high = str;
            }
        }
        System.out.println("low:" + low + " high:" + high);
        System.out.println(sortedSet.subSet(low,high));
        System.out.println(sortedSet.headSet(high));
        System.out.println(sortedSet.tailSet(low));
    }
}

interface Generator<T> {
    T next();
}
class QueueBehavior {
    static void test(Queue queue, Gen gen) {
        for(int i=0;i<gen.size();i++) {
            queue.offer(gen.next());
        }
        while(queue.peek()!=null) {
            System.out.print(" " + queue.remove());
        }
        System.out.println("");
    }
    static class Gen implements Generator<String> {
        String[] str = "one two three four five".split(" ");
        int i = 0;
        public int size() {
            return str.length;
        }
        @Override
        public String next() {
            return str[i++];
        }
    }
    public static void chapter17_7() {
        test(new LinkedList(), new Gen());
        test(new PriorityQueue(), new Gen());
    }
}

class ToDoItem implements Comparable<ToDoItem> {
    private char primary;
    private int secondary;
    private String item;

    public ToDoItem(String td, char pri, int sec) {
        primary = pri;
        secondary = sec;
        item = td;
    }
    @Override
    public int compareTo(ToDoItem o) {
        if(primary > o.primary) {
            return 1;
        }else if(primary == o.primary) {
            if(secondary > o.secondary) {
                return 1;
            }else if(secondary == o.secondary) {
                return 0;
            }
        }
        return -1;
    }
    public String  toString() {
        return Character.toString(primary) + secondary + " :" + item;
    }
}
class ToDoList extends PriorityQueue<ToDoItem> {
    void add(String td, char pri, int sec) {
        super.add(new ToDoItem(td,pri,sec));
    }
    public static void chapter17_7() {
        ToDoList toDoList = new ToDoList();
        toDoList.add("han1",'C',4);
        toDoList.add("han2",'A',1);
        toDoList.add("han3",'A',2);
        toDoList.add("han4",'B',3);
        toDoList.add("han5",'B',4);
        while(!toDoList.isEmpty()){
            System.out.println("" + toDoList.remove());
        }
    }
}

class Maps {
    static void printKeys(Map<Integer,String> map) {
        System.out.println("map.size():" + map.size());
        System.out.println("map.keySet():" + map.keySet());
        System.out.println("map.values():" + map.values());
    }
    static void test(Map<Integer,String> map) {
        map.put(3,"han3");
        map.put(4,"han4");
        map.put(1,"han1");
        map.put(2,"han2");
        printKeys(map);

        Integer first = map.keySet().iterator().next();
        map.remove(first);
        printKeys(map);

        map.clear();
        printKeys(map);
    }
    public static void chapter17_8() {
        System.out.println("HashMap:");
        test(new HashMap<>());
        System.out.println("LinkedHashMap:");
        test(new LinkedHashMap<>());
        System.out.println("TreeMap:");
        test(new TreeMap<>());
    }
}

class CountedString {
    private static List<String> created = new ArrayList<>();
    private String s;
    private int id = 0;

    public CountedString(String str) {
        s = str;
        created.add(s);
        for(String t:created) {
            if(t.equals(s)) {
                id++;
            }
        }
    }

    public String toString() {
        return "String:" + s + " id:" + id + " hashCode():" + hashCode();
    }

    public int hashCode() {
        int result = 17;
        result = 37*result + s.hashCode();
        result = 37*result + id;
        return result;
    }

    public boolean equals(Object o) {
        return o instanceof CountedString && s.equals(((CountedString)o).s) && id == ((CountedString)o).id;
    }

    public static void chapter17_9() {
        Map<CountedString,Integer> map = new HashMap<>();
        CountedString[] cs = new CountedString[5];
        for(int i=0;i<cs.length;i++) {
            cs[i] = new CountedString("ha");
            map.put(cs[i],i);
        }
        System.out.println(map);

        for(CountedString c:cs) {
            System.out.println("key:" + c + " value:" + map.get(c));
        }
    }
}

class Utilities {
    public static void chapter17_11() {
        List<String> list = Arrays.asList("one Two three Four five".split(" "));
        System.out.println("list:" + list);
        System.out.println("disjoint:" + Collections.disjoint(list,Collections.singletonList("Four")));
        System.out.println("max:" + Collections.max(list));
        System.out.println("max_insensitive:" + Collections.max(list,String.CASE_INSENSITIVE_ORDER));
        System.out.println("min:" + Collections.min(list));
        System.out.println("min_insensitive:" + Collections.min(list,String.CASE_INSENSITIVE_ORDER));

        List<String> sublist = Arrays.asList("Four five".split(" "));
        System.out.println("indexOfSubList:" + Collections.indexOfSubList(list,sublist));
        Collections.reverse(list);
        System.out.println("reverse:" + list);
        Collections.rotate(list,2);
        System.out.println("rotate:" + list);

        List<String> src = Arrays.asList("xxx yyy zzz".split(" "));
        Collections.copy(list,src);
        System.out.println("copy:" + list);
        Collections.swap(list,0,list.size()-1);
        System.out.println("swap:" + list);
        Collections.shuffle(list,new Random(7));
        System.out.println("shuffle:" + list);
    }
}

class FailFast {
    public static void chapter17_11() {
        Collection<String> c = new ArrayList<>();
        Iterator<String> it = c.iterator();
        c.add("ha");
        try {
            it.next();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e:" + e.getMessage() + " " + e.toString());
        }
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
class DirList {
    public static void chapter18_1() {
        File path = new File("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh");
        System.out.println("path:" + path.toString());
        String[] list = path.list(new DirFilter(".*\\.java"));
        System.out.println("list.length:" + list.length);
        Arrays.sort(list);
        for(String s:list) {
            System.out.println("s:" + s);
        }
    }
}

class BufferedInputFile {
    static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=in.readLine())!=null) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }
    public static void chapter18_6() {
        try {
            System.out.println(read("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\Main.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MemoryInput {
    public static void chapter18_6() throws IOException {
        StringReader in = new StringReader("xxxyyyzzz");
        int c;
        while((c=in.read())!=-1) {
            System.out.print((char)c);
        }
        System.out.println();
    }
}

class BasicFileOutput {
    public static void chapter18_6() throws IOException {
        BufferedReader in = new BufferedReader(new StringReader("xxx xxx yyy yyy zzz zzz"));
        String file = "D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BasicFile";
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        int line = 1;
        String s;
        while( (s=in.readLine())!=null ) {
            out.println( (line++) + ":" + s );
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}

class StoringAndRecoveringData {
    public static void chapter18_6() throws IOException {
        String file = "D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\Data.txt";
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        out.writeDouble(3.14);
        out.writeUTF("this is pi");
        out.writeDouble(1.414);
        out.writeUTF("square root of 2");
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}

class TextFile {
    // Read a file as a single string
    static String read(String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()));
            // try...finally
            try {
                String s;
                while( (s=in.readLine())!=null ) {
                    sb.append(s);
                    sb.append("\n");
                }
            }finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // Write a single file
    static void write(String filename, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                out.print(text);
            }finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void chapter18_7() {
        String str = read("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BasicFile");
        System.out.println("TextFile:" + str);
        write("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\TextFile", str);
    }
}

class Redirecting {
    public static void chapter18_8() throws IOException {
        // default
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while( (s=br.readLine())!=null ) {
            System.out.println(s);
            if(s.equals("quit")) {
                // "quit" to quit
                break;
            }
        }

        // redirect
        System.out.println("redirect:");
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\TextFile"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\Redirecting")));
        System.setIn(in);
        System.setOut(out);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        String s1;
        while ( (s1=br1.readLine())!=null ) {
            // redirect to out file
            System.out.println(s1);
        }
        out.close();
        System.setOut(console);
    }
}

class GetChannel {
    public static void chapter18_10() throws IOException {
        FileChannel fc = new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\GetChannel").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes(StandardCharsets.UTF_8)));
        fc.close();

        fc = new RandomAccessFile("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\GetChannel","rw").getChannel();
        // Move to the end
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("\nSome more".getBytes(StandardCharsets.UTF_8)));
        fc.close();

        fc = new FileInputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\GetChannel").getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        fc.read(buf);
        // read(buf)后需要调用buf.flip,让buf做好让别人读取字节的准备
        buf.flip();
        while(buf.hasRemaining()) {
            System.out.print((char)buf.get());
        }
        System.out.println();
    }
}

class ChannelCopy {
    public static void chapter18_10() throws IOException {
        FileChannel in = new FileInputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\GetChannel").getChannel();
        FileChannel out = new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\ChannelCopy").getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while(in.read(buf) != -1) {
            buf.flip();
            out.write(buf);
            buf.clear();
        }
    }
}

class BufferToText {
    public static void chapter18_10() throws IOException {
        //(1)write[getBytes()] and read
        FileChannel fc = new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BufferToText").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        fc = new FileInputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BufferToText").getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        fc.read(buf);
        buf.flip();
        //(i)
        System.out.println(buf.asCharBuffer());
        //(ii)
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + ":" + Charset.forName(encoding).decode(buf));

        //(2)write[getBytes("UTF-16BE")] and read
        fc = new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BufferToText").getChannel();
        fc.write(ByteBuffer.wrap("Some text2".getBytes("UTF-16BE")));
        fc.close();
        fc = new FileInputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BufferToText").getChannel();
        buf.clear();
        fc.read(buf);
        buf.flip();
        System.out.println(buf.asCharBuffer());

        //(3)write[buf.asCharBuffer().put()] and read
        fc = new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BufferToText").getChannel();
        buf.asCharBuffer().put("Some text3");
        fc.write(buf);
        fc.close();
        fc = new FileInputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\BufferToText").getChannel();
        buf.clear();
        fc.read(buf);
        buf.flip();
        System.out.println(buf.asCharBuffer());
    }
}

class GetData {
    public static void chapter18_10() {
        // allocate default value 0
        ByteBuffer bb = ByteBuffer.allocate(1024);

        //(1)char array
        bb.rewind();    //bytebuffer回到0位置
        bb.asCharBuffer().put("xxxxxxx");
        System.out.println(bb.asCharBuffer());
        char c;
        while((c = bb.getChar()) != 0) {
            System.out.print(c);
        }
        System.out.println();

        //(2)short
        bb.rewind();
        bb.asShortBuffer().put((short) 2423);
        System.out.println(bb.getShort());

        //(3)int
        bb.rewind();
        bb.asIntBuffer().put(12314);
        System.out.println(bb.getInt());

        //(4)long
        bb.rewind();
        bb.asLongBuffer().put(123123139);
        System.out.println(bb.getLong());

        //(5)double
        bb.rewind();
        bb.asDoubleBuffer().put(77.777);
        System.out.println(bb.getDouble());
    }
}

class IntBufferDemo {
    public static void chapter18_10() {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        IntBuffer ib = bb.asIntBuffer();
        ib.put(new int[]{11,42,47,99,143,811});
        System.out.println(ib.get(3));

        ib.put(3,777);
        ib.flip();
        while(ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }
    }
}

class ViewBuffers {
    public static void chapter18_10() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,97});
        bb.rewind();
        System.out.println("ByteBuffer:");
        while(bb.hasRemaining()) {
            System.out.print(bb.position() + "->" + bb.get() + " ");
        }
        System.out.println();

        bb.rewind();
        CharBuffer cb = bb.asCharBuffer();
        System.out.println("CharBuffer:");
        while(cb.hasRemaining()) {
            System.out.print(cb.position() + "->" + cb.get() + " ");
        }
        System.out.println();

        bb.rewind();
        ShortBuffer sb = bb.asShortBuffer();
        System.out.println("ShortBuffer:");
        while(sb.hasRemaining()) {
            System.out.print(sb.position() + "->" + sb.get() + " ");
        }
        System.out.println();

        bb.rewind();
        IntBuffer ib = bb.asIntBuffer();
        System.out.println("IntBuffer:");
        while(ib.hasRemaining()) {
            System.out.print(ib.position() + "->" + ib.get() + " ");
        }
        System.out.println();

        bb.rewind();
        FloatBuffer fb = bb.asFloatBuffer();
        System.out.println("FloatBuffer:");
        while(fb.hasRemaining()) {
            System.out.print(fb.position() + "->" + fb.get() + " ");
        }
        System.out.println();

        bb.rewind();
        LongBuffer lb = bb.asLongBuffer();
        System.out.println("LongBuffer:");
        while(lb.hasRemaining()) {
            System.out.print(lb.position() + "->" + lb.get() + " ");
        }
        System.out.println();

        bb.rewind();
        DoubleBuffer db = bb.asDoubleBuffer();
        System.out.println("DoubleBuffer:");
        while(db.hasRemaining()) {
            System.out.print(db.position() + "->" + db.get() + " ");
        }
        System.out.println();
    }
}

class UsingBuffers {
    static void symmetricSramble(CharBuffer buf) {
        while(buf.hasRemaining()) {
            buf.mark();
            char c1 = buf.get();
            char c2 = buf.get();
            buf.reset();
            buf.put(c2).put(c1);
        }
    }
    public static void chapter18_10() {
        char[] data = "UsingBuffers".toCharArray();
        // char = 2 * byte
        ByteBuffer bb = ByteBuffer.allocate(data.length*2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        cb.rewind();
        System.out.println(cb);
        // encode
        symmetricSramble(cb);
        cb.rewind();
        System.out.println(cb);
        // decode
        symmetricSramble(cb);
        cb.rewind();
        System.out.println(cb);
    }
}

class FileLocking {
    public static void chapter18_10() throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("D:\\Users\\hanyuhang\\IdeaProjects\\test\\src\\thinkinjava\\hyh\\FileLocking.txt");
        FileLock fl = fos.getChannel().tryLock();
        if(fl != null) {
            System.out.println("Lock file!");
            TimeUnit.MILLISECONDS.sleep(2000);
            // false:独占锁
            System.out.println("fl.isShared():" + fl.isShared());
            fl.release();
            System.out.println("Lock release!");
        }
        fos.close();
    }
}

enum Shrubbery {
    GROUND,
    CRAWLING,
    HANGING
}
class EnumClass {
    public static void chapter19_1() {
        for(Shrubbery shrubbery:Shrubbery.values()) {
            System.out.println("========>" + shrubbery);
            System.out.println(shrubbery.name());
            System.out.println(shrubbery.ordinal());
            System.out.println(shrubbery.compareTo(Shrubbery.CRAWLING));
            System.out.println(shrubbery.getDeclaringClass());
        }
    }
}

enum Explore {
    HERE,
    THERE
}
class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("--------- Analyzing " + enumClass + "----------");

        System.out.println("Interfaces:");
        for(Type t:enumClass.getGenericInterfaces()) {
            System.out.println(t);
        }

        System.out.println("Base:" + enumClass.getSuperclass());

        System.out.println("Methods:");
        Set<String> methods = new TreeSet<>();
        for(Method m:enumClass.getMethods()) {
            methods.add(m.getName());
        }
        System.out.println(methods);
        return methods;
    }
    public static void chapter19_4() {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        exploreMethods.removeAll(enumMethods);
        System.out.println("exploreMethods:" + exploreMethods);
    }
}

enum ConstantSpecificMethod {
    DATETIME {
        @Override
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        @Override
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();

    public static void chapter19_10() {
        for(ConstantSpecificMethod constantSpecificMethod:ConstantSpecificMethod.values()) {
            System.out.println(constantSpecificMethod.name() + " " + constantSpecificMethod.getInfo());
        }
    }
}

class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + countDown + ")" + " ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
        }
    }
}
class MainThread {
    public static void chapter21_2() {
        LiftOff lauch = new LiftOff();
        lauch.run();
    }
}


