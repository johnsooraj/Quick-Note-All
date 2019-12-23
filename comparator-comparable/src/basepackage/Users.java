package basepackage;

public class Users implements Comparable<Users> {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public Users() {
    }

    public Users(Long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Users o) {
        System.out.println("check: " + this.getFirstName() + "-" + o.getFirstName());
        int res = this.getFirstName().compareToIgnoreCase(o.getFirstName());
        if (res != 0)
            return res;
        return this.getFirstName().compareToIgnoreCase(o.getFirstName());
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
