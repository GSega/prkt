package com.project.prkt.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
/**
 * @author Sergei Gavrilov
 */
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
    @CollectionTable(name="rider_types_of_equipment", joinColumns= {@JoinColumn(name="rider_id")})
    private List<TypesOfEquipment> equipmentNeededIds;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_equipment_id", referencedColumnName = "id")
    private AssignedEquipment assignedEquipment;

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

    public AssignedEquipment getAssignedEquipment() {
        if (assignedEquipment == null) {
            return new AssignedEquipment();
        }
        return assignedEquipment;
    }

    public void setAssignedEquipment(AssignedEquipment assignedEquipment) {
        this.assignedEquipment = assignedEquipment;
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
