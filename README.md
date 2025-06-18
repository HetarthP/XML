# 🚗 XML Car Inventory Manager (Java)

This Java desktop application lets users manage and display a car inventory using XML. The program reads car data from an XML file, parses it using Java’s DOM parser, and presents it in a simple GUI.

## 💡 Features

- 📄 Reads and parses car data from an `inventory.xml` file
- 🧾 Displays car details (make, model, year, price, etc.)
- ✅ Uses Java DOM parsing
- 🖥️ Simple Swing-based user interface
- 💾 Easily extendable to include car additions, deletions, or edits

## 🛠️ Technologies Used

- Java (JDK 17+)
- Java Swing
- Java DOM XML Parser
- NetBeans IDE

## 📁 Project Structure

```
CarInventoryXML/
├── src/
│   └── carxml/
│       ├── Main.java
│       ├── Car.java
│       ├── XMLReader.java
│       └── CarInventoryUI.java
├── inventory.xml
└── README.md
```

## 🧪 Sample XML Format

```xml
<cars>
  <car>
    <make>Toyota</make>
    <model>Corolla</model>
    <year>2020</year>
    <price>22000</price>
  </car>
  <car>
    <make>Honda</make>
    <model>Civic</model>
    <year>2019</year>
    <price>21000</price>
  </car>
</cars>
```

## ▶️ How to Run

1. Clone or download the project folder
2. Open in NetBeans (or any Java IDE)
3. Build and run `Main.java`
4. View car listings loaded from `inventory.xml`

## 📌 Notes

- Make sure the `inventory.xml` file is in the root project directory
- You can customize `XMLReader.java` to support attributes or advanced features


