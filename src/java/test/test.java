/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import web.model.Project;
import web.model.ProjectJpaController;

/**
 *
 * @author User
 */
public class test {

    public static void main(String[] args) {
        ProjectJpaController projectJpaController = new ProjectJpaController();
        System.out.println(JSONArray.toJSONString(projectJpaController.list()));
    }
}
