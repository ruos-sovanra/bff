package istad.co.toolservice.feature.gitlab.dto;

import lombok.Builder;

@Builder
public record CreateGroupRequest(
        String groupName,
        String path
) {
}
