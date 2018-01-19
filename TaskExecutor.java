package threading;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {

	public void processFiles(File[] files) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		for (File fileName : files) {
			executorService.execute(new LoadFile(fileName));
		}
		System.out.println("Completed!");

	}

}
