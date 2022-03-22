package app.entity;

import java.util.Objects;

public class Account {

    private String name;
    private String phone;
    private String password;
    private int savings;

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", saving=" + savings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return savings == account.savings &&
                Objects.equals(name, account.name) &&
                Objects.equals(phone, account.phone) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, password, savings);
    }
}
