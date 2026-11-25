package com.planner5d.steps;

import com.planner5d.api.CRUDsAPI;
import com.planner5d.ui.ProjectPage;

public class DeleteProjectSteps {

    public static int deleteProject(String projectKey){
        ProjectPage.backToAllProject();
        return CRUDsAPI.deletResponse(projectKey).statusCode();
    }
}
