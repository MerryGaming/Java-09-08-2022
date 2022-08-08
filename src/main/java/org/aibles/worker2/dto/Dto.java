package org.aibles.worker2.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * */
@Data
@Getter
@Setter
public class Dto { //implements Serializable {
    @NotBlank
    @Size(min = 2, max = 128)
    private String name;

    private int date;
    private int years_of_work;

    @NotBlank
    @Size(min = 5, max = 200)
    private String address;

    private double wage;
    private double allowance;

    public Dto() {}

    public Dto(String name, int date, int years_of_work,
               String address, double wage, double allowance) {
        this.name = name;
        this.date = date;
        this.years_of_work = years_of_work;
        this.address = address;
        this.wage = wage;
        this.allowance = allowance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dto dto = (Dto) o;
        return date == dto.date
                && years_of_work == dto.years_of_work
                && Double.compare(dto.wage, wage) == 0
                && Double.compare(dto.allowance, allowance) == 0
                && name.equals(dto.name)
                && address.equals(dto.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, years_of_work, address, wage, allowance);
    }

    @Override
    public String toString() {
        return "WorkerDto{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", years_of_work=" + years_of_work +
                ", address='" + address + '\'' +
                ", wage=" + wage +
                ", allowance=" + allowance +
                '}';
    }
}
