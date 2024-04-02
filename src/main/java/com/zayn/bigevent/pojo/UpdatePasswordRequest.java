package com.zayn.bigevent.pojo;

import com.zayn.bigevent.annotations.Password;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zayn
 * * @date 2024/3/31/22:26
 */
@Data
public class UpdatePasswordRequest {
    @NotNull
    @Password
    private String oldPwd;
    
    @NotNull
    @Password
    private String newPwd;
    
    @NotNull
    @Password
    private String cfmPwd;
}
