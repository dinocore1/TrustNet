package com.devsmart.trustnet;

import com.google.common.hash.Hasher;

public class Trust implements Comparable<Trust>, Hashable {


    public static Trust TENET = fromInt(1);
    public static Trust ONE_TRUST = fromInt(100000000);

    public final int intValue;

    /**
     *
     * @param value in Tenet units (smallest divisible unit of trust equal to 1e-8)
     * @return
     */
    public static Trust fromInt(int value) {
        return new Trust(value);
    }

    private Trust(int value) {
        intValue = value;
    }

    public Trust add(Trust t) {
        return new Trust(intValue + t.intValue);
    }

    public Trust subtract(Trust t) {
        return new Trust(intValue - t.intValue);
    }

    public Trust multiply(Trust t) {
        return new Trust(intValue * t.intValue);
    }

    public Trust multiply(int x) {
        return new Trust(intValue * x);
    }

    public Trust divide(Trust t) {
        return new Trust(intValue / t.intValue);
    }

    public Trust divide(int x) {
        return new Trust(intValue / x);
    }

    public Trust mod(Trust t) {
        return new Trust(intValue % t.intValue);
    }

    public Trust mod(int x) {
        return new Trust(intValue % x);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != Trust.class) {
            return false;
        }

        Trust o = (Trust) obj;
        return intValue == o.intValue;
    }

    @Override
    public int compareTo(Trust o) {
        return intValue - o.intValue;
    }

    @Override
    public int hashCode() {
        return intValue;
    }

    @Override
    public String toString() {
        return String.format("%.8fT", intValue / 1e8);
    }


    @Override
    public void addToHash(Hasher hasher) {
        hasher.putInt(intValue);
    }
}
