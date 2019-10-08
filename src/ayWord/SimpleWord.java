package ayWord;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class SimpleWord {
	public static void main(String[] args) {
		String filePath = "E:/test.docx";
		String content = readWord(filePath);
		//System.out.println(content);
		for (int i = 0; i < content.length(); i++) {
			try {
				Thread.sleep(1 * 1000);
				System.out.print(content.charAt(i));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static String readWord(String path) {
		String buffer = "";
		try {
			if (path.endsWith(".doc")) {
				InputStream is = new FileInputStream(new File(path));
				WordExtractor ex = new WordExtractor(is);
				buffer = ex.getText();
				ex.close();

			} else if (path.endsWith(".docx")) {
				OPCPackage opcPackage = POIXMLDocument.openPackage(path);
				POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
				buffer = extractor.getText();

				extractor.close();
			} else {
				System.out.println("此文件不是word文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

}
