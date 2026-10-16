//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    MyHashMap<Integer,String > map = new MyHashMap<>();

    map.put(1,"apple");
    map.put(2,"banana");
    map.put(3,"cherry");

    System.out.println(map.get(1));
    System.out.println(map.get(2));
    System.out.println(map.get(3));


    System.out.println( "\n" + "remove:" + map.remove(1) +"\n");

    System.out.println(map.get(1));
    System.out.println(map.get(2));
    System.out.println(map.get(3));
}
