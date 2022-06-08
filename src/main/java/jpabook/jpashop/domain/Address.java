package jpabook.jpashop.domain;


import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 내장되어져있다.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상 만들거긴 때문에 new 생성하지 해당 클래스를 사용하지 말자.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
