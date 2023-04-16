package driver;

import editor.document.EditorDocument;
import editor.document.EditorDocumentLoader;
import editor.document.EditorDocumentWriter;
import editor.document.Text;
import editor.enums.Color;
import editor.enums.FontType;
import editor.exceptions.DocumentParseException;
import editor.exceptions.DocumentWriteException;
import editor.exceptions.EditorException;
import editor.factories.FontColorFactory;
import editor.factories.FontFactory;
import editor.factories.FontSizeFactory;
import editor.interfaces.Document;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Document document = generateEditorDocument();
        testDocumentContent(document);
        testDocumentStorage(document);
    }

    private static Document generateEditorDocument() {
        EditorDocument document = new EditorDocument();
        Text[] textContent = generateTestTextContent();

        for (Text text : textContent) {
            document.addTextSpan(text);
        }

        return document;
    }

    private static Text[] generateTestTextContent() {
        return new Text[]{
            new Text(
                "Hello",
                FontFactory.getFont(FontType.ARIAL),
                FontColorFactory.getFontColor(Color.RED)
            ),
            new Text(
                "World",
                FontFactory.getFont(FontType.ARIAL),
                FontColorFactory.getFontColor(Color.BLACK)
            ),
            new Text(
                "CS",
                FontFactory.getFont(FontType.VERDANA),
                FontColorFactory.getFontColor(Color.BLUE)
            ),
            new Text(
                "5800",
                FontFactory.getFont(FontType.CALIBRI),
                FontSizeFactory.getFontSize(12)
            )
        };
    }

    private static void testDocumentStorage(Document document) {
        System.out.println("TESTING FILE STORAGE");
        try {
            File tempFile = getTempFile();
            System.out.println("COMMIT DOCUMENT TO FILE");
            writeDocumentToFile(document, tempFile);
            System.out.println("LOADING DOCUMENT FILE");
            Document loadedDocument = loadDocument(tempFile);
            testDocumentContent(loadedDocument);
        } catch(EditorException e) {
            e.printStackTrace();
        }
    }

    private static File getTempFile() throws EditorException {
        File tempFile;
        try {
            tempFile = File.createTempFile("temp", "document");
        } catch(IOException e) {
            throw new EditorException("Failed to create temp file", e);
        }
        tempFile.deleteOnExit();
        return tempFile;
    }

    private static void writeDocumentToFile(Document document, File file) throws EditorException {
        EditorDocumentWriter writer = new EditorDocumentWriter();
        try {
            writer.write(document, file);
        } catch(DocumentWriteException e) {
            throw new EditorException("Failed to write document", e);
        }
    }

    private static Document loadDocument(File file) throws EditorException {
        EditorDocumentLoader loader = new EditorDocumentLoader();
        try {
            return loader.load(file);
        } catch(DocumentParseException e) {
            throw new EditorException("Failed to load document", e);
        }
    }

    private static void testDocumentContent(Document document) {
        System.out.println("DOCUMENT: ");
        System.out.println(document.toString());
    }
}