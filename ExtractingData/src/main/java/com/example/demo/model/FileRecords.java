package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "file_records")
public class FileRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(name = "records", columnDefinition = "jsonb")
    private List<String> records = new ArrayList<>();

    public FileRecords() {
        // default constructor required by JPA
    }

    public FileRecords(List<String> records) {
        this.records = records != null ? new ArrayList<>(records) : new ArrayList<>();
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
        this.records = records != null ? new ArrayList<>(records) : new ArrayList<>();
    }

    // Utility methods used by other components

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
        return this.records != null ? this.records.size() : 0;
    }

    public void clearRecords() {
        if (this.records != null) {
            this.records.clear();
        }
    }

    public boolean containsRecord(String record) {
        return this.records != null && this.records.contains(record);
    }

    public List<String> getAllRecords() {
        return this.records != null ? Collections.unmodifiableList(this.records) : Collections.emptyList();
    }

    @Override
    public String toString() {
        return "FileRecords{id=" + id + ", records=" + records + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileRecords)) return false;
        FileRecords that = (FileRecords) o;
        return Objects.equals(id, that.id) && Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, records);
    }
}