package com.group50.common;

public class ResultInfo {
    public static final Integer UNKNOWN_CODE = 100;
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 400;
    public static final Integer NON_EXIST_USER_CODE = 401;
    public static final Integer WRONG_PHONE_VERIFICATION_CODE = 402;
    public static final Integer NON_LOGIN_CODE = 403;

    public static final String UNKNOWN_MSG = "Unknown exception, contact jiale";
    public static final String SUCCESS_MSG = "Success";
    public static final String FAIL_MSG = "Fail";
    public static final String NON_EXIST_USER_MSG = "This user does not exist, please check your username or password first";
    public static final String WRONG_PHONE_VERIFICATION_MSG = "Wrong verification code, please enter again";
    public static final String NON_LOGIN_MSG = "You should first login";
}
