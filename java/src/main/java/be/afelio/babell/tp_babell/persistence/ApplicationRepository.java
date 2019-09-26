package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRepository {
    public List<ProjectDto> findAllProject() {
        List<ProjectDto> projectList = null;

        return projectList;
    }
}
