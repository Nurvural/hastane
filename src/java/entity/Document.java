package entity;

public class Document {

    private int id;
    private String filePath;//dosyanın hangi diractorda bulunduğu
    private String fileName;//dosyanın adı
    private String fileType;//dosyanın tipi

    public Document() {
    }

    public Document(int id, String filePath, String fileName, String fileType) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
