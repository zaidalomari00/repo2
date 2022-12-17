package com.mhawi.slider.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

/**
 * MyWebInitializer.java
 *
 * @author Malek Yaseen <ma.yaseen@ats-ware.com>
 * @since Oct 31, 2021
 */
public class MyWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	private final int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB


	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {

		// upload temp file will put here
		File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

		// register a MultipartConfigElement
		MultipartConfigElement multipartConfigElement =
				new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
						maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

		registration.setMultipartConfig(multipartConfigElement);

	}

	@Override protected Class<?>[] getRootConfigClasses() {
		return new Class[0];
	}

	@Override protected Class<?>[] getServletConfigClasses() {
		return new Class[0];
	}
}
