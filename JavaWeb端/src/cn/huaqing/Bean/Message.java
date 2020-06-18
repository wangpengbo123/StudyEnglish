package cn.huaqing.Bean;

public class Message {
    private int id;
    private String name;
    private String content;
    private String remark;
    private int remarkStatus;
    private int deleteStatus;
    public Message() {
    }

    public Message(int id, String name, String content, String remark) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemarkStatus() {
        return remarkStatus;
    }

    public void setRemarkStatus(int remarkStatus) {
        this.remarkStatus = remarkStatus;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", remarkStatus=" + remarkStatus +
                ", deleteStatus=" + deleteStatus +
                '}';
    }
}
