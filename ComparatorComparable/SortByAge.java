package basepackage;

import java.util.Comparator;

public class SortByAge implements Comparator<Users> {
    @Override
    public int compare(Users o1, Users o2) {
        if (o1.getAge() == o2.getAge()) {
            return 0;
        }
        if (o1.getAge() < o2.getAge()) {
            return 1;
        } else {
            return -1;
        }
    }
}
