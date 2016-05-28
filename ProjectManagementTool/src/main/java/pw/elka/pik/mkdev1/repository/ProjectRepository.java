package pw.elka.pik.mkdev1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pw.elka.pik.mkdev1.domain.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.elka.pik.mkdev1.domain.User;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAllByUsers(User user, Pageable pageable);
    Optional<Project> findOneById(Long id);
}
