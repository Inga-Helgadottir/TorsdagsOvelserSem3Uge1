public class User {
    public String name;
    public String lastName;
    public String password;
    public String phoneNbr;
    public String address;

    public User(String name, String lastName, String password, String phoneNbr, String address) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.phoneNbr = phoneNbr;
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNbr='" + phoneNbr + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
