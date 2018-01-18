package com.hizliyol.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepClone {

	
	public static <T> T deepClone(T object) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(object);
		oos.flush();
		oos.close();
		bos.close();
		byte[] byteData = bos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		return (T) new ObjectInputStream(bais).readObject();
	}
}
