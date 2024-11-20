package istad.co.toolservice.feature.gitlab;


import istad.co.toolservice.feature.gitlab.dto.CreateGroupRequest;
import istad.co.toolservice.feature.gitlab.dto.CreateProjectRequest;

public interface GitLabService {

    void createGitLabGroup(CreateGroupRequest createGroupRequest);

    void createGitLabProject(CreateProjectRequest createProjectRequest);

}
