package com.kaifei.optimization.ifcondition;

/**
 * UserType enum
 */
public enum UserType {
    NORMAL_USER(1), VIP_USER(2), INNER_USER(3);

    private int typeId;

    UserType(int typeId) {
        this.typeId = typeId;
    }
}
