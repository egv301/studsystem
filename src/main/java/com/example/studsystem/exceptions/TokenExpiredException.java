package com.example.studsystem.exceptions;

public class TokenExpiredException extends Exception {
	public TokenExpiredException(String title) {
		super(title);
	}
}
