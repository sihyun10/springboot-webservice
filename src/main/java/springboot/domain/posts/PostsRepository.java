package springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/* JPA에서는 인터페이스를 생성 후, JpaRepository<Entity 클래스 PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨 */
/* [주의] Entity 클래스와 기본 Entity Repository는 함께 위치해야한다! */
/* 그 이유는 Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다. */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
