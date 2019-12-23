package basepackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MasterClass {

    public static List<Users> users = new ArrayList<>();

    static {
        users.add(new Users(1l, "John", "Xavier", 26));
        users.add(new Users(2l, "Yoosuf", "KC", 27));
        users.add(new Users(3l, "Anandu", "S", 26));
        users.add(new Users(4l, "Alen", "Jose", 25));
        users.add(new Users(5l, "Geordy", "James", 25));
        users.add(new Users(6l, "Chikku", "S", 35));
        users.add(new Users(7l, "Nish", "KS", 30));
    }

    public static void getSorted() {
        users.forEach(System.out::println);
        System.out.println("---------------------");
        //Collections.sort(users);

        Collections.sort(users, new SortByAge());

        users.forEach(System.out::println);


    }

    public static void main(String[] args) {
        MasterClass.getSorted();
    }
}
