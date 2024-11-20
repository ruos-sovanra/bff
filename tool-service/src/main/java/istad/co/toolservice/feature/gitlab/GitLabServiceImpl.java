package istad.co.toolservice.feature.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import istad.co.toolservice.config.GitLabConfig;
import istad.co.toolservice.domain.GitLabGroupName;
import istad.co.toolservice.domain.GitLabProject;
import istad.co.toolservice.feature.gitlab.dto.CreateGroupRequest;
import istad.co.toolservice.feature.gitlab.dto.CreateProjectRequest;
import istad.co.toolservice.feature.gitlab.dto.GitLabGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GitLabServiceImpl implements GitLabService{

    private final GitLabRepository gitLabRepository;
    private final GitLabConfig gitLabConfig;
    private final RestTemplate restTemplate;
    private final GitLabProjectRepository gitLabProjectRepository;

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + gitLabConfig.getPersonalAccessToken());
        headers.set("Content-Type", "application/json");
        return headers;
    }


    @Override
    public void createGitLabGroup(CreateGroupRequest createGroupRequest) {


        GitLabGroupName gitLabGroupName = new GitLabGroupName();

        String url = gitLabConfig.getBaseUrl() + "/groups";

        Map<String, String> requestBody = Map.of(
                "name", createGroupRequest.groupName(),
                "path", createGroupRequest.path()
        );

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GitLabGroupResponse gitLabGroupResponse = objectMapper.readValue(response.getBody(), GitLabGroupResponse.class);
            int projectId = gitLabGroupResponse.getId();
            gitLabGroupName.setProjectId(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gitLabGroupName.setGroupName(createGroupRequest.groupName());
        gitLabGroupName.setPath(createGroupRequest.path());
        gitLabGroupName.setUuid(UUID.randomUUID().toString());

        gitLabRepository.save(gitLabGroupName);
    }

    @Override
    public void createGitLabProject(CreateProjectRequest createProjectRequest) {

        GitLabProject gitLabProject = new GitLabProject();

        GitLabGroupName gitLabGroupName = gitLabRepository.findByGroupName(createProjectRequest.groupName()).orElseThrow(() -> new RuntimeException("Group not found"));

        String url = gitLabConfig.getBaseUrl() + "/projects";

        Map<String, String> requestBody = Map.of(
                "name", createProjectRequest.projectName(),
                "namespace_id", String.valueOf(gitLabGroupName.getProjectId())
        );

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        gitLabProject.setProjectName(createProjectRequest.projectName());
        gitLabProject.setUuid(UUID.randomUUID().toString());
        gitLabProject.setGitLabGroupName(gitLabGroupName);

        gitLabProjectRepository.save(gitLabProject);

    }

}
