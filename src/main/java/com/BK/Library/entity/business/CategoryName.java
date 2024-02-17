package com.BK.Library.entity.business;

public enum CategoryName {

    ACTION("AKSİYON"),
    FICTION("KURGU"),
    NON_FICTION("KURGUSAL OLMAYAN"),
    MYSTERY("GİZEM"),
    SCIENCE_FICTION("BİLİM KURGU"),
    ROMANCE("ROMANTİK"),
    HISTORY("TARİH"),
    SELF_HELP("KİŞİSEL GELİŞİM"),
    ADVENTURE("MACERA"),
    HORROR("KORKU"),
    CHILD("ÇOCUK"),
    BIOGRAPHY("BİYOGRAFİ"),
    TRAVEL("GEZİ"),

    CRIME("SUÇ"),

    DYSTOPIAN("DİSTOPYA");


    private final String displayName;

    CategoryName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

