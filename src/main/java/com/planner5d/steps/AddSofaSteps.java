package com.planner5d.steps;

import com.planner5d.ui.ProjectPage;

public class AddSofaSteps {

    public static String addSofaToProject() {
        ProjectPage.chooseFurnishButton();
        ProjectPage.chooseRoomButton();
        ProjectPage.chooseSofasButton();
        ProjectPage.chooseStraightSofasButton();
        return ProjectPage.chooseFirstSofas();

    }
}
