package com.project.prkt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table
public class Rider {
    public Rider() {
    }

    enum Size{
    OTHER,
    RU36_EU37_MM235,
    RU34_EU35_MM240,
    RU37_EU38_MM245,
    RU38_EU39_MM250,
    RU39_EU40_MM255,
    RU40_EU41_MM260,
    RU41_EU42_MM265,
    RU415_EU425_MM275,
    RU42_EU43_MM275,
    RU425_EU435_MM280,
    RU43_EU44_MM285,
    RU44_EU45_MM290,
    RU45_EU46_MM300,
    RU46_EU47_MM310,
}

    enum Sex{
        MALE,
        FEMALE
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    private String name;
    private Sex sex;
    private Integer height;
    private Integer weight;
    private Size foot;
    @ManyToMany(mappedBy = "listOfRiders")
    private List<Booking> listOfBookings;
    //показали какой класс в коллекции и кактой то fetch хз что это
    @ElementCollection(targetClass = TypesOfEquipment.class, fetch = FetchType.EAGER)
    //назвали колонку. в базе появилась types_of_eqipment вместо кэмел кейса
    //@Column(name="typesOfEquipment", nullable=false)
    //указали имя дочерней таблицы и имя колонки которая будет связывать основнйо класс и дочернюю коллекцию
    @CollectionTable(name="riderTypesOfEquipment", joinColumns= {@JoinColumn(name="rider_id")})
    private List<TypesOfEquipment> equipmentNeededIds;
    //в итоге в базе появились таблица rider (класс помечен @Table),
    //появилсь таблица rider_types_of_equipment с колонками rider_id (соединена с rider_id) и types_of_equipment
    /*{
        equipmentNeeded.add(TypesOfEquipment.JACKET);
        equipmentNeeded.add(TypesOfEquipment.SKI);
    } //захардкодил что б было чему в бд записыватсья
*/

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Size getFoot() {
        return foot;
    }

    public void setFoot(Size foot) {
        this.foot = foot;
    }

    public List<TypesOfEquipment> getEquipmentNeededIds() {
        return equipmentNeededIds;
    }

    public void setEquipmentNeededIds(List<TypesOfEquipment> equipmentNeededIds) {
        this.equipmentNeededIds = equipmentNeededIds;
    }

    public List<Booking> getListOfBookings() {
        return listOfBookings;
    }

    public void setListOfBookings(List<Booking> listOfBookings) {
        this.listOfBookings = listOfBookings;
    }

    public void addToListOfBookings(Booking booking) {
        if (listOfBookings == null) {
            listOfBookings = new ArrayList<>();
        }
        this.listOfBookings.add(booking);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rider rider)) return false;
        return id.equals(rider.id) && name.equals(rider.name) && sex == rider.sex && height.equals(rider.height) && weight.equals(rider.weight) && foot == rider.foot && equipmentNeededIds.equals(rider.equipmentNeededIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, height, weight, foot, equipmentNeededIds);
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                ", foot=" + foot +
                ", equipmentNeededIds=" + equipmentNeededIds +
                '}';
    }
}
