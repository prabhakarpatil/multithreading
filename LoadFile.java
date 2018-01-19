package threading;

import java.io.File;

public class LoadFile implements Runnable {

	private File fileName;

	public LoadFile() {

	}

	public LoadFile(File fileName2) {
		super();
		this.fileName = fileName2;
	}

	@Override
	public void run() {

		FiletoDBReader fl = new FiletoDBReader();
		try {
			fl.loadfileintoDB(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
