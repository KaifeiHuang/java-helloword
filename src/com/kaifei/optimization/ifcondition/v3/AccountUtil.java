package com.kaifei.optimization.ifcondition.v3;

import com.kaifei.optimization.ifcondition.UserType;

import java.util.function.Function;

public class AccountUtil {


    public static Object accountForNormalUser(UserType userTpe){
        System.out.println("accountForNormalUser ---- ");
        return UserType.NORMAL_USER;
    }

    public static Object accountForVipUser(UserType userTpe){
        System.out.println("accountForVipUser ---- ");
        return UserType.VIP_USER;
    }

    public Object accountForInnerUser(UserType userTpe){
        System.out.println("accountForInnerUser ---- ");
        return UserType.INNER_USER;
    }



}
