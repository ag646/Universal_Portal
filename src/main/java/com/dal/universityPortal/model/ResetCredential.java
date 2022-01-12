package com.dal.universityPortal.model;

public class ResetCredential extends Credential {

    private Integer resetCode;

    public Integer getResetCode() {
        return resetCode;
    }
    public void setResetCode(Integer resetCode) {
        this.resetCode = resetCode;
    }
}
