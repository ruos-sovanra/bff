package istad.co.toolservice.feature.gitlab;

import istad.co.toolservice.feature.gitlab.dto.CreateGroupRequest;
import istad.co.toolservice.feature.gitlab.dto.CreateProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gitlab")
@RequiredArgsConstructor
public class GitLabController {

    private final GitLabService gitLabService;


    @PostMapping("/create-group")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGitLabGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        gitLabService.createGitLabGroup(createGroupRequest);
    }

    @PostMapping("/create-project")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGitLabProject(@RequestBody CreateProjectRequest createProjectRequest) {
        gitLabService.createGitLabProject(createProjectRequest);
    }


}
