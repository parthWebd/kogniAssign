package com.kog.assignment.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface ServiceHelpInt {
	
	public byte[] getDownloadedImage() throws IOException ;
	public byte[] compressBytes(byte[] data);
	public byte[] decompressBytes(byte[] data);
	

}
