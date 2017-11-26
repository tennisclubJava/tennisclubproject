package com.frenchies.tennisclub.dto;

/**
 * The DTO object for updating user password.
 *
 * @author  thomasmeon 473449
 */
public class UserUpdatePasswordDTO {
    // TODO: constraints
    private Long id;
    private String oldPassword;
    private String newPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}