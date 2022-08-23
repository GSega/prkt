package com.project.prkt.model;

public abstract class Gear extends Equipment {
    enum Size{};
    enum Stiffness{SOFT, MEDIUM, HARD};
    Size size;
    Stiffness stiffness;
}
