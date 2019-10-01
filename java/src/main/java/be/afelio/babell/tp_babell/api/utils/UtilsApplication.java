package be.afelio.babell.tp_babell.api.utils;

import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UtilsApplication {

    public List<ProjectDto> createProjectListDto(List<ProjectEntity> projectEntitiesList) {
        List<ProjectDto> projectDtosList = new ArrayList<>();
        for (ProjectEntity project: projectEntitiesList) {
            projectDtosList.add(ProjectDto.from(project));
        }
        return projectDtosList;
    }
}
