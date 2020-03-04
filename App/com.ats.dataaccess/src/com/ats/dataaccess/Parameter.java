package com.ats.dataaccess;

/**
 * Parameter Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class Parameter implements IParameter {
    private Object value;
    private Direction direction;
    private int sqlType;

    Parameter() {
        this.direction = Direction.IN;
    }

    Parameter(Object value) {
        this.value = value;
        this.direction = Direction.IN;
    }

    Parameter(Object value, Direction direction) {
        this.value = value;
        this.direction = direction;
    }

    Parameter(Object value,Direction direction, int sqlType) {
        this.value = value;
        this.direction = direction;
        this.sqlType = sqlType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setSQLType(int sqlType) {
        this.sqlType = sqlType;
    }

    @Override
    public int getSQLType() {
        return this.sqlType;
    }
}
