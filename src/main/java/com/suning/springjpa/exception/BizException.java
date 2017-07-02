package com.suning.springjpa.exception;

public class BizException extends Exception {
    private static final long serialVersionUID = 5985952179617589700L;

    public BizException(String string) {
	super(string);
    }
}