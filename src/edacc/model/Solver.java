package edacc.model;

import java.io.File;


public class Solver extends BaseModel implements IntegerPKModel {
    private int id;
    private String name;
    private String binaryName;
    private File binaryFile;
    private String description;
    private String md5;
    private File codeFile;
    
    public Solver() {
        this.setNew();
    }

    public String getBinaryName() {
        return binaryName;
    }

    public void setBinaryName(String binaryName) {
        this.binaryName = binaryName;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    protected File getBinaryFile() {
        return binaryFile;
    }

    public void setBinaryFile(File binaryFile) {
        this.binaryFile = binaryFile;
        if (this.isSaved()) {
            this.setModified();
        }
    }

    protected File getCodeFile() {
        return codeFile;
    }

    public void setCodeFile(File codeFile) {
        this.codeFile = codeFile;
        if (this.isSaved()) {
            this.setModified();
        }
    }
}
