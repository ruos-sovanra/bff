package istad.co.toolservice.feature.gitlab;

import istad.co.toolservice.domain.GitLabGroupName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitLabRepository extends JpaRepository<GitLabGroupName, Long> {

    Optional<GitLabGroupName> findByGroupName(String groupName);

}
