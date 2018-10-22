package com.revature.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface User {
	public User login(String password);
	public void logout(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException ;
	public void service(String toCall, HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException;
	public ArrayList<String> display(); 
}
