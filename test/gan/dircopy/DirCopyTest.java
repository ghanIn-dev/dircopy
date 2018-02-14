package gan.dircopy;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirCopyTest {
	DirCopy dircopy;

	@BeforeEach
	void setUp() throws Exception {
		dircopy = new DirCopy();
	}

	@Test
	void getCurrentDirLocation() {
		assertEquals("D:\\workspace\\DirCopy", dircopy.getDirLocation());
	}

	@Test
	void getSrcDirAndTarDir() {
		String dir[] = { "D:\\workspace\\DirCopy\\SrcDir", "D:\\workspace\\DirCopy\\TarDir" };
		assertEquals(dir[1], dircopy.getSrcAndTarDir("SrcDir", "TarDir"));
	}
	
	@Test
	void getSrcDirFileList() {
		String fileList[] = {"a.txt","b.txt","c.txt"};
		assertEquals(fileList[2], dircopy.getSrcFileList("SrcDir"));
	}
	

}