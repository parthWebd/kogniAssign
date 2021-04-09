package com.kog.assignment.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.utility.RandomString;
@Service
public class MultiPartImage implements MultipartFile {

	private byte[] bytes;
	String name;
	String originalFilename;
	String contentType;
	boolean isEmpty;
	long size;

//	public MultiPartImage(byte[] bytes, String name, String originalFilename, String contentType,
//	        long size) {
//	    this.bytes = bytes;
//	    this.name = name;
//	    this.originalFilename = originalFilename;
//	    this.contentType = contentType;
//	    this.size = size;
//	    this.isEmpty = false;
//	}

	@Override
	public String getName() {
	    return name;
	}

	@Override
	public String getOriginalFilename() {
	    return originalFilename;
	}

	@Override
	public String getContentType() {
	    return contentType;
	}

	@Override
	public boolean isEmpty() {
	    return isEmpty;
	}

	@Override
	public long getSize() {
	    return size;
	}

	@Override
	public byte[] getBytes() throws IOException {
	    return bytes;
	}

	@Override
	public InputStream getInputStream() throws IOException {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
	    // TODO Auto-generated method stub

	}

	
}