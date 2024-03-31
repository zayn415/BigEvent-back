package com.zayn.bigevent.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zayn
 * * @date 2024/3/31/22:26
 */
@Data
public class UpdatePasswordRequest {
    @NotNull
    private String oldPwd;
    @NotNull
    private String newPwd;
    @NotNull
    private String cfmPwd;
}
