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

	public void addOffer(Offer offer) throws IOException {
		// TODO Auto-generated method stub
		byte[] downloadedImage = getDownloadedImage();
		offer.setPicByte(compressBytes(downloadedImage));
		offerRepo.save(offer);
	}

	public byte[] getDownloadedImage() throws IOException {

		URL imageUrl = new URL("https://via.placeholder.com/600/92c952");
//		BufferedImage image = ImageIO.read(imageUrl);

		Image image = ImageIO.read(imageUrl.openStream());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write((RenderedImage) image, "jpg", byteArrayOutputStream);
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();

		System.out.println("Multipart = " + byteArrayOutputStream.toByteArray().length);
		return byteArrayOutputStream.toByteArray();

	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	// uncompress the image bytes 
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}

	public List<Offer> getAllOffer() {
		ArrayList<Offer> list = (ArrayList<Offer>) offerRepo.findAll();
		return list;
	}

}
