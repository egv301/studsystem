package com.example.studsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studsystem.dto.GroupDTO;
import com.example.studsystem.exceptions.GroupExistsException;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Group;
import com.example.studsystem.repo.GroupRepository;

@Service
public class GroupService {
	@Autowired
	GroupRepository groupRepository;
	
	public List<Group> groupList(){
		List<Group> groupList = groupRepository.findAll();
    	return groupList;
    }
	
    public void addGroup(GroupDTO groupObj) throws GroupExistsException {
    	Group group = groupRepository.findByTitle(groupObj.getTitle());
    	if (group!=null) {
    		throw new GroupExistsException("Group with same title exists");
    	}
    	groupRepository.save(new Group(groupObj.getTitle()));
    }
    
    
    
    public Group getGroup(Long group_id) throws NotFoundException {
    	Group group = groupRepository.findById(group_id).orElseThrow(()->new NotFoundException("Group not found"));
    	return group;
    }
    
    public void updateGroup(GroupDTO groupObj) throws GroupExistsException, NotFoundException {
    	Group group = groupRepository.findById(groupObj.getId()).orElseThrow(()->new NotFoundException("Group not found"));
    	
    	if(groupRepository.findByNotTitle(groupObj.getId(), groupObj.getTitle())!=null) {
    		throw new GroupExistsException("Group with same title exists");
    	}
    	group.setTitle(groupObj.getTitle());
    	groupRepository.save(group);
    	
    }
    
    public void deleteGroup(Long group_id) throws NotFoundException {
    	Group group = groupRepository.findById(group_id).orElseThrow(()->new NotFoundException("Group was not found"));
    	groupRepository.deleteById(group_id);
    }
}
