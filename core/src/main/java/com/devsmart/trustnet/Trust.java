package com.devsmart.trustnet;

public class Trust implements Comparable<Trust> {


    public static Trust TENET = fromInt(1);
    public static Trust ONE_TRUST = fromInt(100000000);

    private int mValue;

    /**
     *
     * @param value in Tenet units (smallest divisible unit of trust equal to 1e-8)
     * @return
     */
    public static Trust fromInt(int value) {
        return new Trust(value);
    }

    private Trust(int value) {
        mValue = value;
    }

    public Trust add(Trust t) {
        return new Trust(mValue + t.mValue);
    }

    public Trust subtract(Trust t) {
        return new Trust(mValue - t.mValue);
    }

    public Trust multiply(Trust t) {
        return new Trust(mValue * t.mValue);
    }

    public Trust multiply(int x) {
        return new Trust(mValue * x);
    }

    public Trust divide(Trust t) {
        return new Trust(mValue / t.mValue);
    }

    public Trust divide(int x) {
        return new Trust(mValue / x);
    }

    public Trust mod(Trust t) {
        return new Trust(mValue % t.mValue);
    }

    public Trust mod(int x) {
        return new Trust(mValue % x);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != Trust.class) {
            return false;
        }

        Trust o = (Trust) obj;
        return mValue == o.mValue;
    }

    @Override
    public int compareTo(Trust o) {
        return mValue - o.mValue;
    }

    @Override
    public int hashCode() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.format("%.8fT", mValue / 1e8);
    }


}
