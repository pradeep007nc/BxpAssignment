package com.bxp.assinment.MetroBookingBackend.model;

import com.bxp.assinment.MetroBookingBackend.enums.ActiveStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "passenger")
public class Passenger extends BaseEntity {
    private String name;
    private String email;
    private String phone;
    private String address;
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;

    @OneToMany(mappedBy = "passenger")
    @ToString.Exclude
    private List<Ticket> tickets;
}
