package com.example.final_test.Project;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {
	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping()
	public List<Project> getProjects() {
		return this.projectService.getProjects();
	}

	@PostMapping(path = "/add")
	public void addProject(@RequestBody Project project) {
		projectService.addProject(project);
	}

	@PutMapping(path = "{project_id}/update")
	public void updateProject(@PathVariable("project_id") Integer project_id,
			@RequestParam(required = false) String project_name, @RequestParam(required = false) LocalDate dueDate) {
		projectService.updateProject(project_id, project_name, dueDate);
	}

	@GetMapping(path = "/empCount")
	public String getCount(@RequestParam(required = false) String project_name,
			@RequestParam(required = false) Integer project_id) {

		return "Count [project_id=" + project_id + ", count: " + projectService.getCount(project_name, project_id)
				+ "]";
	}

}
