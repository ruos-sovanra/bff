package istad.co.toolservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "gitlab_project")
@Data
public class GitLabProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "gitlab_group_name_id")
    private GitLabGroupName gitLabGroupName;
}
