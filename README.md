# Hibernate BLOB & CLOB Mapping (LOB Handling)

This project demonstrates how to work with **Large Objects (LOBs)** in Hibernate by storing and retrieving:

* 📸 Images using **BLOB (Binary Large Object)**
* 🧾 Text files using **CLOB (Character Large Object)**

It includes a complete flow from **file → database → file reconstruction**.

---

## 🚀 Tech Stack

* Java 17
* Hibernate ORM (6.4.4.Final)
* MySQL
* Maven

---

## 📌 Key Concepts Covered

* `@Lob` annotation for large data
* Mapping:

  * `byte[]` → BLOB (image)
  * `char[] / String` → CLOB (text)
* File handling:

  * `FileInputStream` for binary data
  * `FileReader` for text data
* Writing files using:

  * `FileOutputStream`
  * `FileWriter`

---

## 🧱 Project Structure

hibernate-blob-clob-demo/

│
├── src/
│   ├── com.isha.model/
│   │     └── StudentInfo.java
│   │
│   ├── com.isha.app/
│   │     ├── LaunchStandardApp.java   (Insert LOB data)
│   │     └── LOBRetrievalApp.java     (Retrieve & recreate files)
│
├── output/
│   ├── retrieved_oggy.png
│   └── retrieved_bio.txt
│
├── pom.xml
└── hibernate.cfg.xml

---

## 🧪 How It Works

### 🔹 1. Store Data (Insert)

* Reads image file → converts to `byte[]`
* Reads text file → converts to `char[]`
* Stores in database using Hibernate

```java
si.setsImage(image);
si.setsBio(bio);
session.persist(si);
```

---

### 🔹 2. Retrieve Data

* Fetches data using:

```java
StudentInfo si = session.get(StudentInfo.class, 1);
```

* Writes back to files:

```java
fos.write(si.getsImage());
fw.write(si.getsBio());
```

---

## 📂 Output

After running retrieval:

* Image is recreated → `/output/retrieved_oggy.png`
* Text is recreated → `/output/retrieved_bio.txt`

---

## ⚠️ Issues Faced & Fixes

### ❌ Data Truncation Error

Data too long for column 'student_image'

### ✅ Fix:

Use explicit column definition:

```java
@Column(columnDefinition = "LONGBLOB")
private byte[] sImage;

@Column(columnDefinition = "LONGTEXT")
private char[] sBio;
```

---

## 💡 Key Learnings

* Binary data must be handled using streams
* Not all file data should be stored in DB (large files should use cloud storage)
* Hibernate may default to smaller types like `TINYBLOB` if not specified
* Always verify data before persistence

---

## 🧠 Interview Takeaway

This project demonstrates end-to-end handling of large objects in Hibernate, including storage, retrieval and file reconstruction.

---

## 📌 Author

Isha Parihariya
