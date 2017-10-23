package forjun.com.adaptive.tools;

import java.io.File;

/**
 * Created by Administrator on 2017/10/23.
 */

class FileUtils {
	public static void deleteDirectory(File file) {
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			for(File subFile: listFiles){
				deleteDirectory(subFile);
			}
			file.delete();
		}else{
			file.delete();
		}
		
	}
}
