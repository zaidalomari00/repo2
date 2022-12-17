package com.mhawi.slider.business.dto;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * File.java
 *
 * @author Malek Yaseen <ma.yaseen@ats-ware.com>
 * @since Oct 13, 2021
 */
public class FileMap implements Serializable, Map<String, Object> {

	public static String FILE_NAME = "fileName";
	public static String FILE_INPUT_STREAM = "fileInputStream";
	public static String DURATION = "Duration";
	public static String CONTENT = "CONTENT";
	public static String FILE_SIZE = "fileSize";
	public static String FILE_CONTENT_TYPE = "fileContentType";
	private final Map<String, Object> files = new HashMap<>();

	public static <T> FileMap.Builder<T> builder() {
		return new FileMap.Builder<>();
	}

	@Override public int size() {
		return files.size();
	}

	@Override public boolean isEmpty() {
		return files.isEmpty();
	}

	@Override public boolean containsKey(Object key) {
		return files.containsKey(key);
	}

	@Override public boolean containsValue(Object value) {
		return files.containsValue(value);
	}

	@Override public Object get(Object key) {
		return files.get(key);
	}

	@Override public Object put(String key, Object value) {
		return files.put(key, value);
	}

	@Override public Object remove(Object key) {
		return files.remove(key);
	}

	@Override public void putAll(Map<? extends String, ?> m) {
		files.putAll(m);
	}

	@Override public void clear() {
		files.clear();

	}

	@Override public Set<String> keySet() {
		return files.keySet();
	}

	@Override public Collection<Object> values() {
		return files.values();
	}

	@Override public Set<Entry<String, Object>> entrySet() {
		return files.entrySet();
	}

	public String getFileName() {
		return (String) files.get(FILE_NAME);
	}

	public InputStream getFileInputStream() {
		return (InputStream) files.get(FILE_INPUT_STREAM);
	}

	public byte[] getContent() {
		return (byte[]) files.get(CONTENT);
	}

	public long getFileSize() {
		return (long) files.get(FILE_SIZE);
	}

	public String getFileContentType() {
		return (String) files.get(FILE_CONTENT_TYPE);
	}

	public static final class Builder<T> {

		private final FileMap files = new FileMap();

		public FileMap.Builder fileName(String fileName) {
			files.put(FILE_NAME, fileName);
			return this;
		}

		public FileMap.Builder fileContent(byte[] content) {
			files.put(CONTENT, content);
			return this;
		}

		public FileMap.Builder fileContentType(String contentType) {
			files.put(FILE_CONTENT_TYPE, contentType);
			return this;
		}

		public FileMap.Builder fileSize(long size) {
			files.put(FILE_SIZE, size);
			return this;
		}

		public FileMap.Builder fileInputStream(InputStream inputStream) {
			files.put(FILE_INPUT_STREAM, inputStream);
			return this;
		}
		public FileMap.Builder fileDuration(Double dd) {
			files.put(FILE_INPUT_STREAM, dd);
			return this;
		}

		public FileMap build() {
			return files;
		}


	}

}
