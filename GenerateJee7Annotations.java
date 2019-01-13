package src.kusu.code.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GenerateJee7Annotations {
	
	static String facesXmlPath="X:/web_git/web/vwau/web/WEB-INF/faces-config.xml";
	static String targetElement = "managed-bean";
	static String beanClassFullName;
	static String beanName;
	static String beanScope;
	static String directoryPath = "X:/web_git/web/vwau/src/";
	static HashMap<String,String[]> scopeHashMap = new HashMap<>();
	
	static {
		scopeHashMap.put("application", new String [] {"@ApplicationScoped","import javax.faces.bean.ApplicationScoped;"});
		scopeHashMap.put("session", new String [] {"@SessionScoped","import javax.faces.bean.SessionScoped;"});
		scopeHashMap.put("view", new String [] {"@ViewScoped","import javax.faces.view.ViewScoped;"});
		scopeHashMap.put("request", new String [] {"@RequestScoped","import javax.faces.bean.RequestScoped;"});
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(facesXmlPath);

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName(targetElement);

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				beanClassFullName = eElement.getElementsByTagName("managed-bean-class").item(0).getTextContent();
				beanClassFullName = beanClassFullName.replace(".", "/");
				if (beanClassFullName.contains("web/mgbean")) {
					beanName = eElement.getElementsByTagName("managed-bean-name").item(0).getTextContent();
					beanScope = eElement.getElementsByTagName("managed-bean-scope").item(0).getTextContent();
					StringBuilder l_strBuilder = new StringBuilder();
					String readFileContent = readFileFromDirectory(directoryPath + beanClassFullName + ".java",	directoryPath, l_strBuilder);
					
					readFileContent = readFileContent.substring(0, readFileContent.indexOf(System.lineSeparator()))
									+ System.lineSeparator() + System.lineSeparator() + scopeHashMap.get(beanScope)[1]
									+ System.lineSeparator() + "import javax.inject.Named; " + readFileContent.substring(
									readFileContent.indexOf(System.lineSeparator()), readFileContent.length());
					
					readFileContent = readFileContent.substring(0, readFileContent.indexOf("public class")) + "@Named("
									+ '"' + beanName + '"' + ")" + System.lineSeparator() + scopeHashMap.get(beanScope)[0]
									+ System.lineSeparator() + readFileContent
									.substring(readFileContent.indexOf("public class"), readFileContent.length());


					OutputStream os = null;
					try {
						os = new FileOutputStream(new File(directoryPath + beanClassFullName + ".java"));
						os.write(readFileContent.getBytes(), 0, readFileContent.length());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}
	}
	
	private static String readFileFromDirectory(String className, String directory,StringBuilder strBuilder) throws IOException {
				File file = new File(directory);
				if (!file.isDirectory()) {
					readFile(className, file, strBuilder);
					
				} else {
					File[] listFiles = file.listFiles();
					for (File l_file : listFiles) {
						readFileFromDirectory(className, l_file.getCanonicalPath(),strBuilder);
					}
					
				}
				
		return strBuilder.toString();
	}

	private static void readFile(String className, File file, StringBuilder strBuilder) throws IOException {
		Scanner l_scanner;
		if (file.getCanonicalPath().replace("\\", "/").equals(className)) {
			l_scanner = new Scanner(file);
			while (l_scanner.hasNextLine()) {
				strBuilder.append(l_scanner.nextLine());
				strBuilder.append(System.lineSeparator());
			}
		}
	}

}

