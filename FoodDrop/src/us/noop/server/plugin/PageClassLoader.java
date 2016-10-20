package us.noop.server.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PageClassLoader extends ClassLoader {
	
	@SuppressWarnings("unchecked")
	public Page loadClass(File file) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassFormatError, IOException{
		byte[] data = new byte[(int) file.length()];
		FileInputStream f = new FileInputStream(file);
		f.read(data);
		f.close();
		return (Page) defineClass(null, data, 0, data.length).getConstructor(ty).newInstance();
	}
}
