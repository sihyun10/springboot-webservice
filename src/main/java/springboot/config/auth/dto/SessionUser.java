package springboot.config.auth.dto;

import lombok.Getter;
import springboot.domain.user.User;

import java.io.Serializable;

// SessionUser에는 인증된 사용자 정보만 필요하다.
// 그 외에 필요한 정보들은 없어서 name, email, picture만 필드로 선언한다.

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
