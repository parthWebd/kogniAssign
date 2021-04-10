package com.kog.assignment.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
	@Autowired
	private ServiceHelpInt serviceHelp;

	public Offer addOffer(Offer offer) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside Offer Service");
		byte[] downloadedImage = serviceHelp.getDownloadedImage();
		offer.setPicByte(serviceHelp.compressBytes(downloadedImage));
		Offer of = offerRepo.save(offer);
		return of;
	}

	public List<Offer> getAllOffer() {
		System.out.println("Inside Offer Service : Get All Offer");
		ArrayList<Offer> list = (ArrayList<Offer>) offerRepo.findAll();
		return list;
	}

}
