import java.util.Objects;

public class MyTestingClass{
    private String id;

    public MyTestingClass(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (int i = 0; i < id.length(); i++) {
            hash = 31 * hash + id.charAt(i);
        }
        return hash;
    }

    @Override
    public String toString() {
        return "MyTestingClass{" + "id='" + id + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        return Objects.equals(id, that.id);
    }

}
