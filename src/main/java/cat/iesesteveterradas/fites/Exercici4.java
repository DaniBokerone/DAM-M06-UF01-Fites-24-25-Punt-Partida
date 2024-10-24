package cat.iesesteveterradas.fites;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;


/**
 * Implementa el codi que realitzi el següent:
 * 
 * - Genera documents XML i JSON a partir de la informació sobre llenguatges de programació continguda en una llista.
 * - Guarda l'estructura XML i JSON generada en fitxers a les rutes especificades amb els noms 'Exercici4.xml' i 'Exercici4.json'.
 * - Per crear el fitxer JSON cal usar Jakarta.
 * - Gestió d'errors: si hi ha algun problema en escriure o generar els fitxers, mostra l'excepció a la consola.
 
 Documents esperats (el contingut és important, el format bonic no es te en compte):

 <?xml version="1.0" encoding="UTF-8" standalone="no"?>
<llista>
    <llenguatge dificultat="alta" extensio=".c">
        <nom>C</nom>
        <any>1972</any>
    </llenguatge>
    <llenguatge dificultat="mitjana" extensio=".java">
        <nom>Java</nom>
        <any>1995</any>
    </llenguatge>
    <llenguatge dificultat="baixa" extensio=".js">
        <nom>Javascript</nom>
        <any>1995</any>
    </llenguatge>
    <llenguatge dificultat="baixa" extensio=".m">
        <nom>Objective C</nom>
        <any>1984</any>
    </llenguatge>
    <llenguatge dificultat="mitjana" extensio=".dart">
        <nom>Dart</nom>
        <any>2011</any>
    </llenguatge>
</llista>


[
    {
        "nom": "C",
        "any": "1972",
        "extensio": ".c",
        "dificultat": "alta"
    },
    {
        "nom": "Java",
        "any": "1995",
        "extensio": ".java",
        "dificultat": "mitjana"
    },
    {
        "nom": "Javascript",
        "any": "1995",
        "extensio": ".js",
        "dificultat": "baixa"
    },
    {
        "nom": "Objective C",
        "any": "1984",
        "extensio": ".m",
        "dificultat": "baixa"
    },
    {
        "nom": "Dart",
        "any": "2011",
        "extensio": ".dart",
        "dificultat": "mitjana"
    }
]
*/

public class Exercici4 {
    private String filePathXML;
    private String filePathJson;

    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/exercici4/";
        String filePathXML = basePath + "Exercici4.xml";
        String filePathJson = basePath + "Exercici4.json";

        Exercici4 exercici = new Exercici4();
        exercici.configuraPathFitxerSortidaXML(filePathXML);
        exercici.configuraPathFitxerSortidaJson(filePathJson);
        exercici.executa();
    }

    // Mètode que executa la lògica de la classe
    public void executa() {
        ArrayList<String[]> llista = new ArrayList<>();
        llista.add(new String[]{"C", "1972", ".c", "alta"});
        llista.add(new String[]{"Java", "1995", ".java", "mitjana"});
        llista.add(new String[]{"Javascript", "1995", ".js", "baixa"});
        llista.add(new String[]{"Objective C", "1984", ".m", "baixa"});
        llista.add(new String[]{"Dart", "2011", ".dart", "mitjana"});

        // Crear i guardar el document XML
        Document doc = crearDocumentXML(llista);
        guardarXML(filePathXML, doc);

        // Crear i guardar el document JSON
        guardarJSON(filePathJson, llista);
    }

    // Mètode que crea el document XML a partir de la llista proporcionada
    private Document crearDocumentXML(ArrayList<String[]> llista) {
        // *************** CODI EXERCICI FITA **********************/
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            //Llista
            Element llistaLLenguatges = doc.createElement("llista");
            doc.appendChild(llistaLLenguatges);
        
            llista.forEach((llenguatge)->{

                //Llenguatge
                Element llenguatgeElement = doc.createElement("llenguatge");
                Attr attrDiffId = doc.createAttribute("dificultat");
                Attr attrExtId = doc.createAttribute("extensio");

                attrDiffId.setValue(llenguatge[3]);
                attrExtId.setValue(llenguatge[2]);

                llenguatgeElement.setAttributeNode(attrDiffId);
                llenguatgeElement.setAttributeNode(attrExtId);

                //Elements Llenguatge
                llenguatgeElement.appendChild(createElementTemplate(doc, "nom", llenguatge[0]));
                llenguatgeElement.appendChild(createElementTemplate(doc, "any",llenguatge[1] ));
                llistaLLenguatges.appendChild(llenguatgeElement);

            });
        
            return doc;
    
        }catch (ParserConfigurationException e){
            System.out.println(e.getMessage());            
            return null;
        }
    }

    private static Element createElementTemplate(Document doc, String elementName, String textContent) {
        Element element = doc.createElement(elementName);
        Text textNode = doc.createTextNode(textContent);
        element.appendChild(textNode);
        return element;
    }

    // Escriu un Document en un fitxer XML
    public static void guardarXML(String path, Document doc) {
        // *************** CODI EXERCICI FITA **********************/
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();            
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

            DOMSource source = new DOMSource(doc);
            File saveFile = new File(path);
            StreamResult result = new StreamResult(saveFile);
            
            transformer.transform(source, result);
            
            System.out.println("Document XML guardat correctament");
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }

    // Escriu la llista en un fitxer JSON utilitzant Jakarta
    public void guardarJSON(String path, ArrayList<String[]> llista) {
        // *************** CODI EXERCICI FITA **********************/
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        llista.forEach((llenguatge)->{
            JsonObject personaJson = Json.createObjectBuilder()
                .add("nom", llenguatge[0])
                .add("any", llenguatge[1])
                .add("extensio", llenguatge[2])
                .add("dificultat", llenguatge[3])
                .build();
            arrayBuilder.add(personaJson);
        });


        JsonArray jsonArray = arrayBuilder.build();
        File pathFile= new File(path);
        try (JsonWriter jsonWriter = Json.createWriter(Files.newBufferedWriter(Paths.get(pathFile.getParent(), pathFile.getName())))) {
            jsonWriter.writeArray(jsonArray);
            System.out.println("Document Json guardat correctament");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /****************************************************************************/
    /*                              NO CAL TOCAR                                */
    /****************************************************************************/
    // Retorna els nodes d'una expressió XPath
    public static NodeList getNodeList(Document doc, String expression) {
        NodeList llista = null;
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            llista = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return llista;
    }

    // Setter per definir el path XML
    public void configuraPathFitxerSortidaXML(String filePath) {
        this.filePathXML = filePath;
    }

    // Setter per definir el path JSON
    public void configuraPathFitxerSortidaJson(String filePath) {
        this.filePathJson = filePath;
    }

    // Getter per obtenir el path XML
    public String obtenirRutaFitxerSortidaXML() {
        return this.filePathXML;
    }

    // Getter per obtenir el path JSON
    public String obtenirRutaFitxerSortidaJson() {
        return this.filePathJson;
    }
}
