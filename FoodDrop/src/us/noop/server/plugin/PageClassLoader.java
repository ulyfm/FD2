package us.noop.ulytils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PageClassLoader<T> extends ClassLoader {
	
	@SuppressWarnings("unchecked")
	public T loadClass(File file, Object... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassFormatError, IOException{
		byte[] data = new byte[(int) file.length()];
		FileInputStream f = new FileInputStream(file);
		f.read(data);
		f.close();
		Class<?>[] ty = new Class<?>[args.length];
		for(int i = 0; i < args.length; ++i){
			ty[i] = args[i].getClass();
		}
		return (T) defineClass(null, data, 0, data.length).getConstructor(ty).newInstance(args);
	}
}