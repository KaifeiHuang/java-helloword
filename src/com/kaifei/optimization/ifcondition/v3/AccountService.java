package com.kaifei.optimization.ifcondition.v3;

import com.kaifei.optimization.ifcondition.UserType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AccountService {
    private final static Map<UserType, Function<UserType, Object>> accountFuncMap = new HashMap<>();

    // init accountFuncMap, 后面需要修改只需要增加Map即可
    static {
        accountFuncMap.put(UserType.NORMAL_USER, AccountUtil::accountForNormalUser);
        accountFuncMap.put(UserType.VIP_USER, AccountUtil::accountForVipUser);
//        accountFuncMap.put(UserType.INNER_USER,AccountUtil::accountForInnerUser); ::只能式static method
        accountFuncMap.put(UserType.INNER_USER, userType -> new AccountUtil().accountForInnerUser(userType));
    }

    public static Object account(UserType userType) {
        Function<UserType, Object> userAccountFunction = accountFuncMap.get(userType);
        if (userAccountFunction != null) {
            return userAccountFunction.apply(userType);
        }

        return null;
    }

    @Test
    public void testAccount() {
        UserType userType = UserType.NORMAL_USER;
        Object account = account(userType);
        System.out.println(account.toString());

    }

}
