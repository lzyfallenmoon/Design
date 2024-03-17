package org.example.virtual_study_companion.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class User {
    @NonNull
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;

    @NotEmpty
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
