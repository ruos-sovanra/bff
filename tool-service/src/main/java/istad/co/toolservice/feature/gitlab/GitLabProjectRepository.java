package istad.co.toolservice.feature.gitlab;

import istad.co.toolservice.domain.GitLabProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitLabProjectRepository extends JpaRepository<GitLabProject, Long> {
}
