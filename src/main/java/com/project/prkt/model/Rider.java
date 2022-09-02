package com.project.prkt.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
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

    @Id
    private Long id;
    private String name;
    private String sex;
    private Integer height;
    private Integer weight;
    private Size foot;
    //показали какой класс в коллекции и кактой то fetch хз что это
    @ElementCollection(targetClass = TypesOfEquipment.class, fetch = FetchType.EAGER)
    //назвали колонку. в базе появилась types_of_eqipment вместо кэмел кейса
    @Column(name="typesOfEquipment", nullable=false)
    //указали имя дочерней таблицы и имя колонки которая будет связывать основнйо класс и дочернюю коллекцию
    @CollectionTable(name="rider_typesOfEquipment", joinColumns= {@JoinColumn(name="rider_id")})
    private List<TypesOfEquipment> equipmentNeeded;
    //в итоге в базе появились таблица rider (класс помечен @Table),
    //появилсь таблица rider_types_of_equipment с колонками rider_id (соединена с rider_id) и types_of_equipment






/*– String name
– String sex
– int height
-int weight
– Size foot
-[перечень необходимого оборудования]*/


}
