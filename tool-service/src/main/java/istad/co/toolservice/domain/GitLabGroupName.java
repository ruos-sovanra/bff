package istad.co.toolservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gitlab_group_name")
@Getter
@Setter
@NoArgsConstructor
public class GitLabGroupName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String groupName;
    private String path;
    private Integer projectId;

}
