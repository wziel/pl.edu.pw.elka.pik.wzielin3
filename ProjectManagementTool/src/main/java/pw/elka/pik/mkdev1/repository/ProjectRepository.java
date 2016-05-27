package pw.elka.pik.mkdev1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pw.elka.pik.mkdev1.domain.Project;

import java.time.ZonedDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pw.elka.pik.mkdev1.domain.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findOneByName(String name);
    Page<Project> findAllByUsers(User user, Pageable pageable);
}
