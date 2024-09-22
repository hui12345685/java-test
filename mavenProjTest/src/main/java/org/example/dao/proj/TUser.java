package org.example.dao.proj;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TUser implements Serializable {
    private Long id;
    private String userId;
    private String userName;
    private Integer userAge;
    private Boolean isDeleted;
}
