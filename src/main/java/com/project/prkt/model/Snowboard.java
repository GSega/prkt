package com.project.prkt.model;

public class Snowboard extends Gear{
    enum Arch {CAMBER, FLAT, ROCKER}
    enum BindingSize {S, M, L}
    private Arch arch;
    private BindingSize bindingSize;
}
