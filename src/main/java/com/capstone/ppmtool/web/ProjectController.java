package com.capstone.ppmtool.web;

import com.capstone.ppmtool.domain.Project;
import com.capstone.ppmtool.services.MapValidationErrorService;
import com.capstone.ppmtool.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {



@Autowired
private ProjectService projectService;



@Autowired
private MapValidationErrorService mapValidationErrorService;




@PostMapping("")
public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){



ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
if(errorMap!=null) return errorMap;



Project project1 = projectService.saveOrUpdateProject(project);
return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
}




@GetMapping("/{projectId}")
public ResponseEntity<?> getProjectById(@PathVariable String projectId){



Project project = projectService.findProjectByIdentifier(projectId);



return new ResponseEntity<Project>(project, HttpStatus.OK);
}




@GetMapping("/all")
public Iterable<Project> getAllProjects(){return projectService.findAllProjects();}




@DeleteMapping("/{projectId}")
public ResponseEntity<?> deleteProject(@PathVariable String projectId){
projectService.deleteProjectByIdentifier(projectId);



return new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted", HttpStatus.OK);
}
}