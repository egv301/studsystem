package com.example.studsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.studsystem.dto.GroupDTO;
import com.example.studsystem.exceptions.GroupExistsException;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Group;
import com.example.studsystem.service.GroupService;

@RestController
@RequestMapping("/api/admin")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
	
	@GetMapping("/group-list")
	public ResponseEntity<List<Group>> groupList(){
		List<Group> groupList = groupService.groupList();
		return ResponseEntity.ok(groupList);
	}
	
	@GetMapping("/group/{group_id}")
	public ResponseEntity<Group> getGroup(@PathVariable("group_id") Long group_id) throws NotFoundException{
		Group group = groupService.getGroup(group_id);
		return ResponseEntity.ok(group);
	}
	
	@PostMapping("/add-group")
	public ResponseEntity<?> addGroup(@RequestBody @Valid GroupDTO groupObj) throws GroupExistsException{
		groupService.addGroup(groupObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update-group")
	public ResponseEntity<?> updateGroup(@RequestBody @Valid GroupDTO groupObj) throws NotFoundException,GroupExistsException{
		groupService.updateGroup(groupObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-group/{group_id}")
	public ResponseEntity<?> deleteGroup(@PathVariable("group_id") Long group_id) throws NotFoundException {
		groupService.deleteGroup(group_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
}
