package com.kog.assignment.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kog.assignment.dao.OfferRepo;
import com.kog.assignment.dto.Offer;

import javassist.bytecode.ByteArray;
import net.bytebuddy.utility.RandomString;

@Service
public class OfferService {

	
	@Autowired
	private OfferRepo offerRepo;
	
	public void addOffer(Offer offer) throws IOException {
		// TODO Auto-generated method stub
		byte[] downloadedImage=getDownloadedImage();
		offer.setPicByte(downloadedImage);
		offerRepo.save(offer);
	}
	
	
	public byte[] getDownloadedImage() throws IOException {
		
		URL imageUrl = new URL("https://via.placeholder.com/600/92c952");
		BufferedImage image = ImageIO.read(imageUrl);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image,"jpg",byteArrayOutputStream);
		byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        
		System.out.println("Multipart = "+byteArrayOutputStream.toByteArray().length);
		return byteArrayOutputStream.toByteArray();
		
	}
	
	
	public List<Offer> getAllOffer(){
		ArrayList<Offer> list = (ArrayList<Offer>) offerRepo.findAll();
		return list;
	}

}
