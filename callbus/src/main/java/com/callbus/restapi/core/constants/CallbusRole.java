package com.callbus.restapi.core.constants;

public enum CallbusRole {

    ROLE_REALTOR("REALTOR"),
    ROLE_LESSOR("LESSOR"),
    ROLE_LESSEE("LESSEE");

    String USER_ROLE;

    CallbusRole(String USER_ROLE) {
        this.USER_ROLE = USER_ROLE;
    }

    public String getValue() {
        return this.USER_ROLE;
    }

}
