package istad.co.toolservice.feature.gitlab.dto;

import lombok.Builder;

@Builder
public record CreateProjectRequest(
        String projectName,
        String groupName
) {
}
