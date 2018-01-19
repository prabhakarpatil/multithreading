package threading;

import java.io.File;

public class BatchApplication {

	public static void main(String[] args) {

		File[] files = readfilesintoFileArray();

		TaskExecutor fileLoaderTaskExecutor = new TaskExecutor();
		fileLoaderTaskExecutor.processFiles(files);

	}

	private static File[] readfilesintoFileArray() {

		File folder = new File("C:\\temp\\Files");
		File[] files = folder.listFiles();

		return files;

	}

}
