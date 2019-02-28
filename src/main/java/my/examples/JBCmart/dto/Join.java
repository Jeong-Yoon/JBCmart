package my.examples.JBCmart.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Join {
    @NotNull
    @Size(min = 4, max = 255)
    private String id;
    @NotNull
    @Size(min = 2, max = 255)
    private String name;
    @NotNull
    @Size(min = 11, max = 11)
    private String phone;
    @NotNull
    @Size(min = 4, max = 50)
    private String email;
    @NotNull
    @Size(min = 4,max = 255)
    private String password1;
    @NotNull
    @Size(min = 4,max = 255)
    private String password2;
}

/*

    private String passwd;
    @Column(length = 50)
    private String name;
    @Column(length = 11)
    private String phone;
    @Column(length = 50)
    private String email;
    private Date regdate;
 */