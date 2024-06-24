package me.whiteship.inflearnjavatest.study;

import me.whiteship.inflearnjavatest.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
