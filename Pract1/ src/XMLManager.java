import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

class XMLManager {

    // Создание XML файла с объектом Person
    public static void createXMLWithPerson(String filePath, Person person) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("persons");
            doc.appendChild(root);

            // Добавляем объект Person в XML
            addPersonToXML(doc, root, person);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("XML файл создан успешно с объектом Person.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Добавление объекта Person в существующий XML файл
    public static void addPersonToXMLFile(String filePath, Person person) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc;

            // Если файл существует, загружаем его; если нет, создаем новый документ
            if (file.exists()) {
                doc = db.parse(file);
                doc.getDocumentElement().normalize();
            } else {
                doc = db.newDocument();
                Element root = doc.createElement("persons");
                doc.appendChild(root);
            }

            // Добавляем объект Person в XML
            Element root = doc.getDocumentElement();
            addPersonToXML(doc, root, person);

            // Сохраняем изменения в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Объект Person добавлен в XML файл.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для добавления объекта Person в XML
    private static void addPersonToXML(Document doc, Element root, Person person) {
        Element personElement = doc.createElement("person");

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(person.name));
        personElement.appendChild(name);

        Element age = doc.createElement("age");
        age.appendChild(doc.createTextNode(String.valueOf(person.age)));
        personElement.appendChild(age);

        root.appendChild(personElement);
    }

    // Чтение XML файла
    public static void readXML(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("person");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Имя: " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Возраст: " + element.getElementsByTagName("age").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
