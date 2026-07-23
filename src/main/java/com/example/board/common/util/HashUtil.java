package com.example.board.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.board.common.exception.CustomNotFoundException;

public final class HashUtil {

	private HashUtil() {}

	public static String sha256(String text) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(text.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new CustomNotFoundException("SHA-256 알고리즘을 찾을 수 없습니다", e);
		}

	}
}
