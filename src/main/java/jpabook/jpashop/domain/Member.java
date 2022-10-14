package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded /*얘는 내장타입을 포함했다*/
    private Address address;

    @OneToMany(mappedBy = "member") //Order테이블에 있는 member테이블에 의해 나는 매핑된거야
    private List<Order> orders = new ArrayList<>();

}
