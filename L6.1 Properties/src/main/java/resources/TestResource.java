package resources;

public class TestResource {
    private String _name;
    private int _age;

    public TestResource(String name, int age) {
        this._name = name;
        this._age = age;
    }

    public TestResource() {
        this._name = "";
        this._age = 0;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        _age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResource)) return false;

        TestResource that = (TestResource) o;

        if (_age != that._age) return false;
        return _name != null ? _name.equals(that._name) : that._name == null;

    }

    @Override
    public int hashCode() {
        int result = _name != null ? _name.hashCode() : 0;
        result = 31 * result + _age;
        return result;
    }


    @Override
    public String toString() {
        return "TestResource{" +
                "_name='" + _name + '\'' +
                ", _age=" + _age +
                '}';
    }
}
