package com.noi.utility.spring.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;

import com.noi.utility.hibernate.ImageValue;
import com.noi.utility.spring.dao.ImageValueDao;

public class S3ImageValueService implements ImageValueService {
	
	static Logger logger = Logger.getLogger(S3ImageValueService.class);

	private ImageValueDao imageValueDao;	
	private AWSCredentials awsCredentials;
	private String bucketName;
	private String imagePrefix;

	
	
	@Override
	public List<ImageValue> findAll() {
		throw new RuntimeException("NO IMPL");
	}



	/**
	 * this is to support legacy methods required for the older version
	 * of the client.
	 * 
	 */
	@Override
	public InputStream findImageStream(ImageValue iv) {
	
		try {
			
			S3Service s3Service = new RestS3Service(awsCredentials);
			S3Bucket targetBucket = s3Service.getBucket(bucketName);

	        // Retrieve the whole data object we created previously
	        S3Object objectComplete = 
	        	s3Service.getObject(targetBucket, 
	        			this.imagePrefix+iv.getFilename());

	        // Read the data from the object's DataInputStream using a loop, and print it out.
	        return objectComplete.getDataInputStream();

			
		} catch (S3ServiceException e) {
			logger.error("problem with s3 push of image", e);
		} catch (Exception e) {
			logger.error("problem with s3 push of image", e);
		}
		
		return null;
		
		
	}

	

	/**
	 * 
	 *  THIS COULD BE DONE BETTER
	 *  (non-Javadoc)
	 * @see com.noi.utility.spring.service.ImageValueService#saveImageValue(com.noi.utility.hibernate.ImageValue)
	 */
	public void saveImageValue(ImageValue ivOrig)
	{
		ImageValue iv = null;
		if(ivOrig.getId() != null)
		{
			iv = imageValueDao.findByPrimaryKey(ivOrig.getId());		
			iv.setFilename(ivOrig.getFilename());
		
			try {
				S3Service s3Service = new RestS3Service(awsCredentials);
				S3Bucket targetBucket = s3Service.getBucket(bucketName);
				
				uploadFile(
						s3Service, 
						targetBucket, 
						this.imagePrefix+ivOrig.getFilename(), 
						iv.getContentType(), 
						ivOrig.getImageBytesBuffered());
				
			} catch (S3ServiceException e) {
				logger.error("problem with s3 push of image", e);
			} catch (NoSuchAlgorithmException e) {
				logger.error("problem with s3 push of image", e);
			} catch (IOException e) {
				logger.error("problem with s3 push of image", e);
			} catch (Exception e) {
				logger.error("problem with s3 push of image", e);
			}
			
			//dont set the image
			iv.setImageBytes(new byte[] {});
			imageValueDao.save(iv);
		}
		else
		{
			try {
				S3Service s3Service = new RestS3Service(awsCredentials);
				S3Bucket targetBucket = s3Service.getBucket(bucketName);
				
				uploadFile(
						s3Service, 
						targetBucket, 
						this.imagePrefix+ivOrig.getFilename(), 
						ivOrig.getContentType(), 
						ivOrig.getImageBytesBuffered());
				
			} catch (S3ServiceException e) {
				logger.error("problem with s3 push of image", e);
			} catch (NoSuchAlgorithmException e) {
				logger.error("problem with s3 push of image", e);
			} catch (IOException e) {
				logger.error("problem with s3 push of image", e);
			} catch (Exception e) {
				logger.error("problem with s3 push of image", e);
			}
			
			//dont set the image
			ivOrig.setImageBytes(new byte[] {});
			imageValueDao.save(ivOrig);
		}
		
	}

	
	private void uploadFile(
			S3Service s3Service, 
			S3Bucket targetBucket, 
			String fileName, 
			String contentType, 
			byte[] bytes)
	throws S3ServiceException, NoSuchAlgorithmException, IOException
	{
        // Create an object containing a greeting string as input stream data.
        S3Object fileObject = new S3Object(fileName);
        
        //public
        AccessControlList bucketAcl = s3Service.getBucketAcl(targetBucket);
        bucketAcl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
        fileObject.setAcl(bucketAcl);
        ByteArrayInputStream imageIS = new ByteArrayInputStream(bytes);
        fileObject.setDataInputStream(imageIS);
        fileObject.setContentLength(imageIS.available());
        fileObject.setContentType(contentType);

        // Upload the data objects.
        s3Service.putObject(targetBucket, fileObject);
	}
	
	@Override
	public void deleteImageValue(ImageValue iv) {
		imageValueDao.delete(iv);
		
	}

	public void setImageValueDao(ImageValueDao imageValueDao) {
		this.imageValueDao = imageValueDao;
	}

	@Override
	public ImageValue findImageById(Long id) {
		ImageValue iv = imageValueDao.findByPrimaryKey(id); 
		try {
			//load the byte array
			BufferedInputStream bis = new BufferedInputStream(this.findImageStream(iv));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(bis, baos);
			iv.setImageBytes(baos.toByteArray());
		} catch (IOException e) {
			logger.error("problem getting image",e);
		}		
		
		return iv;
	}

	public void setAwsCredentials(AWSCredentials awsCredentials) {
		this.awsCredentials = awsCredentials;
	}


	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}


	public void setImagePrefix(String imagePrefix) {
		this.imagePrefix = imagePrefix;
	}
	
	
	
}
