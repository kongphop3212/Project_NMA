package com.example.projectnma;

public class NotesModel {
    private String id;
    private String title;
    private String description;
    private String uid;

    // เพิ่มเมทอดที่ไม่มีพารามิเตอร์
    public NotesModel() {
    }

    // เมทอดสร้างอ็อบเจ็กต์ที่มีพารามิเตอร์
    public NotesModel(String id, String title, String description, String uid) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    // เมทอด getter และ setter อื่นๆ ที่เรียกใช้โดยโปรแกรมอื่นๆ

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
