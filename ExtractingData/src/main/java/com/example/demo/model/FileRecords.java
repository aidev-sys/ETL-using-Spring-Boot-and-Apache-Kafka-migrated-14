package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "file_records")
public class FileRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "records", columnDefinition = "jsonb")
    private List<String> records;

    public FileRecords() {
        this.records = new ArrayList<>();
    }

    public FileRecords(List<String> records) {
        this.records = records;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRecords() {
        return records;
    }

    public void setRecords(List<String> records) {
        this.records = records;
    }

    // Additional utility methods required by other components

    public void addRecord(String record) {
        if (this.records == null) {
            this.records = new ArrayList<>();
        }
        this.records.add(record);
    }

    public String getRecord(int index) {
        if (this.records == null) {
            throw new IndexOutOfBoundsException("No records available");
        }
        return this.records.get(index);
    }

    public void setRecord(int index, String value) {
        if (this.records == null) {
            throw new IndexOutOfBoundsException("No records available");
        }
        this.records.set(index, value);
    }

    public String removeRecord(int index) {
        if (this.records == null) {
            throw new IndexOutOfBoundsException("No records available");
        }
        return this.records.remove(index);
    }

    public int getRecordCount() {
        return (this.records == null) ? 0 : this.records.size();
    }
}