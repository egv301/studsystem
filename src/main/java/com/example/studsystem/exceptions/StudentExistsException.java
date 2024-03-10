package com.example.studsystem.exceptions;

public class StudentExistsException extends Exception {
	public StudentExistsException(String title) {
		super(title);
	}
}
