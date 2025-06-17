
//Name: Hetarth Parikh
//Unit,Lesson,Name of Assignment: Unit 3 Lesson 5, XML Assignment
//Date of Completion: October 21st 2024
//Description:This is a program where a user enters car information into input fields
//and it will create an XML file with all the information while also updating the information.
//It can also clear the file, and also added filters to see which is the newest car and the
//car with the most mileage as well. Also added a filter search area to search for cars 
//in any order such as color or company etc and filters for newest car and mileage as well





package ovs.u3l5.xml;


import javax.xml.parsers.DocumentBuilder;  // import the DocumentBuilder class to build XML documents
import javax.xml.parsers.DocumentBuilderFactory;// impport DocumentBuilderFactory to create a DocumentBuilder
import javax.xml.transform.Transformer;//import Transformer for transforming the XML structure into a file
import javax.xml.transform.TransformerFactory;//import TransformerFactory to create a Transformer
import javax.xml.transform.dom.DOMSource;//import DOMSource to specify the source for XML transformation
import javax.xml.transform.stream.StreamResult;//import StreamResult to define the destination (file) for XML output
import org.w3c.dom.Document;//import Document class to represent the XML document
import org.w3c.dom.Element;//import the element class to represent an element in the XML doc
import javax.swing.JOptionPane;//used for window pop ups to inform user
import java.io.File;//import for file handling
import org.w3c.dom.NodeList; //to use nodelist
import org.w3c.dom.Document;//to use doc for the xml file
import org.w3c.dom.Element;//to use elements 
import org.w3c.dom.Node;//to use nodes
import java.util.Arrays;//to use arrays
import java.util.List;//to use lists 
import javax.swing.*;//to use basic java swing features
import javax.swing.border.LineBorder;//to have live validation on the border
import javax.swing.event.DocumentEvent;//to have live validation for the event
import javax.swing.event.DocumentListener;//to have live validation as a doc listener
import java.awt.*;// For layout managers and other components basically
import java.awt.event.WindowAdapter;//also for the x closing error handling and handle window events
import java.awt.event.WindowEvent;//for the x closing error handling and to capture them 
import javax.swing.BorderFactory;// the border live validation
import javax.swing.border.Border;//Also for the border live validation
import java.util.regex.Pattern;//to create patterns to set which fields can take which data type
/**
 *
 * @author user
 */
public class XMLFile extends javax.swing.JFrame {
    
    // Helper method to create an XML element for the car details
    // It takes the XML document (doc), the car element (car), the tag name (tagName), and the value (value) to add
    private void createCarElement(Document doc, Element car, String tagName, String value) {
        // Creating a new element with the specified tag name
        Element element = doc.createElement(tagName);
        // Setting the text value of the element
        element.appendChild(doc.createTextNode(value));
        // Appending the element to the car element in the document
        car.appendChild(element);
    }

    /**
     * Creates new form XML
     */
    public XMLFile() {
        initComponents();
        setSize(800,600);//sets the size of the window
            // Set the default close operation to DO_NOTHING to prevent automatic exit
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
 // Add DocumentListeners to each text field
  // Add DocumentListeners to each text field
// Add DocumentListeners to each text field
Border defaultBorder = textYear.getBorder();
addDocumentListenerForYear(textYear, defaultBorder); // Validate Year
addDocumentListenerForCompany(textComp, defaultBorder); // Validate Company
addDocumentListenerForStyle(textStyle, defaultBorder); // Validate Style
addDocumentListenerForMileage(textMileage, defaultBorder); // Validate Mileage
addDocumentListenerForColor(textColor, defaultBorder); // Validate Color
addDocumentListenerForSearch(textSearch, defaultBorder); // Validate Search

// Add a window listener to handle closing
addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        // Show the confirmation dialog when user clicks the 'X'
        int response = JOptionPane.showConfirmDialog(
                XMLFile.this,
                // This asks the user if they're sure they want to exit
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION, // Displays the yes or no option
                JOptionPane.QUESTION_MESSAGE // This shows the question message
        );

        // If the user clicks yes, then exit the program
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0); // Exit the program
        }
        // If no, just do nothing and keep the application running
    }
});
    }
   // Method to add DocumentListener for Year
private void addDocumentListenerForYear(JTextField textField, Border defaultBorder) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateYearInput(); // validate input on text inserted by the user
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateYearInput(); // validate input on text removed by user
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateYearInput(); // handle style change
        }

        private void validateYearInput() {
            String input = textField.getText().trim(); // get trimmed input

            if (input.isEmpty()) {
                textField.setBorder(defaultBorder); // reset border if empty
                return; // exit the method if empty
            }

            // validate numeric input for year
            try {
                int number = Integer.parseInt(input); // parse input
                if (number < 0 || input.length() > 4) { // check for negative or more than 4 digits
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // set border to red
                } else {
                    textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // set border to green
                }
            } catch (NumberFormatException e) {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // set to red if invalid
            }
        }
    });
}

// Method to add DocumentListener for Company
private void addDocumentListenerForCompany(JTextField textField, Border defaultBorder) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateCompanyInput(); // Validate input on text inserted by the user
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateCompanyInput(); // Validate input on text removed by user
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateCompanyInput(); // Handle style change
        }

        private void validateCompanyInput() {
            String input = textField.getText().trim(); // Get trimmed input

            if (input.isEmpty()) {
                textField.setBorder(defaultBorder); // Reset border if empty
                return; // Exit the method if empty
            }

            // validate input to allow letters and spaces only
            if (Pattern.matches("[a-zA-Z ]+", input)) {
                textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // valid input
            } else {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // invalid input
            }
        }
    });
}

// this is the method to add a DocumentListener for the style text field
private void addDocumentListenerForStyle(JTextField textField, Border defaultBorder) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateStyleInput(); // Validate input on text inserted by the user
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateStyleInput(); // Validate input on text removed by user
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateStyleInput(); // Handle style change
        }

        private void validateStyleInput() {
            String input = textField.getText().trim(); // Get trimmed input

            if (input.isEmpty()) {
                textField.setBorder(defaultBorder); // Reset border if empty
                return; // exit the method if empty
            }

            // Validate input to allow letters, numbers, and spaces
            if (Pattern.matches("[a-zA-Z0-9 ]+", input)) {
                textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // valid input
            } else {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // invalid input
            }
        }
    });
}

// Method to add DocumentListener for Mileage
private void addDocumentListenerForMileage(JTextField textField, Border defaultBorder) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateMileageInput(); // Validate input on text inserted by the user
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateMileageInput(); // Validate input on text removed by user
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateMileageInput(); // Handle style change
        }

        private void validateMileageInput() {
            String input = textField.getText().trim(); // Get trimmed input

            if (input.isEmpty()) {
                textField.setBorder(defaultBorder); // Reset border if empty
                return; // Exit the method if empty
            }

            // Validate numeric input for mileage
            try {
                int number = Integer.parseInt(input); // Parse input
                if (number < 0) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Set border to red
                } else {
                    textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Set border to green
                }
            } catch (NumberFormatException e) {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Set border to red if invalid
            }
        }
    });
}

// Method to add DocumentListener for Color
private void addDocumentListenerForColor(JTextField textField, Border defaultBorder) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateColorInput(); // Validate input on text inserted by the user
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateColorInput(); // Validate input on text removed by user
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateColorInput(); // Handle style change
        }

        private void validateColorInput() {
            String input = textField.getText().trim(); // Get trimmed input

            if (input.isEmpty()) {
                textField.setBorder(defaultBorder); // Reset border if empty
                return; // Exit the method if empty
            }

            // Validate input to allow letters and spaces only
            if (Pattern.matches("[a-zA-Z ]+", input)) {
                textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Valid input
            } else {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Invalid input
            }
        }
    });
}

// This is the method to add a document listener specifically for the textSearch field
private void addDocumentListenerForSearch(JTextField textField, Border defaultBorder) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateSearchInput(); // Validate input on text inserted by the user
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateSearchInput(); // Validate input on text removed by user
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateSearchInput(); // Handle style change
        }

        private void validateSearchInput() {
            String input = textField.getText().trim(); // Get trimmed input

            if (input.isEmpty()) {
                textField.setBorder(defaultBorder); // Reset border if empty
                return; // Exit the method if empty
            }

            // Validate input to allow letters, numbers, and spaces only
            if (Pattern.matches("[a-zA-Z0-9 ]+", input)) {
                textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Valid input
            } else {
                textField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Invalid input
            }
        }
    });
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textYear = new javax.swing.JTextField();
        textComp = new javax.swing.JTextField();
        textColor = new javax.swing.JTextField();
        textStyle = new javax.swing.JTextField();
        textMileage = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchResultsArea = new javax.swing.JTextArea();
        btnSearch = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnYear = new javax.swing.JButton();
        btnMileage = new javax.swing.JButton();
        btnClearFields = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Car Inventory");

        textYear.setBackground(new java.awt.Color(153, 204, 255));

        textComp.setBackground(new java.awt.Color(153, 204, 255));

        textColor.setBackground(new java.awt.Color(153, 204, 255));

        textStyle.setBackground(new java.awt.Color(153, 204, 255));

        textMileage.setBackground(new java.awt.Color(153, 204, 255));

        btnCreate.setText("Create new XML File");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update XML");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel2.setText("Year");

        jLabel3.setText("Style");

        jLabel4.setText("Colour");

        jLabel5.setText("Company");

        jLabel6.setText("Mileage");

        searchResultsArea.setEditable(false);
        searchResultsArea.setColumns(20);
        searchResultsArea.setRows(5);
        jScrollPane1.setViewportView(searchResultsArea);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        textSearch.setBackground(new java.awt.Color(153, 204, 255));

        btnClear.setText("Clear File");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnYear.setText("Newest");
        btnYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYearActionPerformed(evt);
            }
        });

        btnMileage.setText("Most mileage");
        btnMileage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMileageActionPerformed(evt);
            }
        });

        btnClearFields.setText("Clear fields");
        btnClearFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFieldsActionPerformed(evt);
            }
        });

        jLabel7.setText("Search for car!");

        jLabel8.setText("Filters");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(btnSearch))))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textColor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textYear, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textStyle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                .addComponent(textMileage, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textComp, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnClearFields)
                                .addGap(126, 126, 126)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnClear)
                                .addGap(83, 83, 83)
                                .addComponent(btnYear)
                                .addGap(18, 18, 18)
                                .addComponent(btnMileage)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(textYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(textComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textMileage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearFields))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(36, 36, 36)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear)
                    .addComponent(btnYear)
                    .addComponent(btnMileage))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //the following code will be to validate the inputs from the user
private boolean validateInputs() {
    // makes sure the year is only numbers and checks if it's exactly 4 digits
    if (!textYear.getText().matches("\\d{4}")) { // checks for exactly 4 digits
        // Show user error message
        JOptionPane.showMessageDialog(this, "Year must be a 4-digit number. Please enter all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return false; // return false to indicate a not valid input
    }

    // validate mileage
    if (!textMileage.getText().matches("\\d+")) { // checks if it's only numbers
        // Show user message
        JOptionPane.showMessageDialog(this, "Mileage must be a positive whole number. Please enter all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return false; // returns false if not valid input
    }

    // validate company (letters and spaces allowed)
    if (!textComp.getText().matches("[a-zA-Z ]+")) { // allow letters and spaces
        // Show user message
        JOptionPane.showMessageDialog(this, "Company must contain only letters and spaces. Please enter all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return false; // return false for invalid input
    }

    // validate the color (letters and spaces allowed)
    if (!textColor.getText().matches("[a-zA-Z ]+")) { // allow letters and spaces
        // Show user message
        JOptionPane.showMessageDialog(this, "Color must contain only letters and spaces. Please enter all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return false; // return false to indicate invalid input
    }

      // validate style (alphanumeric and spaces allowed)
    if (!textStyle.getText().matches("[a-zA-Z0-9 ]+")) { // allow letters, numbers, and spaces
        // Show user error to make sure it's alphanumeric and spaces
        JOptionPane.showMessageDialog(this, "Style must contain only letters, numbers, and spaces. Please enter all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return false; // returns false if not valid
    }

    // All validations passed
    return true;

}
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
         // Validate inputs before proceeding with the file creation
    if (!validateInputs()) {
        return; // exit if invalid
    }   //create a factory to create a new XML doc
         try {
             //setting the docfactory(new instance)
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        //new doc builder
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // this makes the XML document
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Inventory");//sets title
        doc.appendChild(rootElement);//appends the root element to the doc

        // Create a new car element to store all the details basically
        Element car = doc.createElement("Car");
        rootElement.appendChild(car);//appends the car element to the root element

        // this just adds elements for the car's details
        createCarElement(doc, car, "Year", textYear.getText());//gets the year input
        createCarElement(doc, car, "Company", textComp.getText());//gets the company input
        createCarElement(doc, car, "Style", textStyle.getText());//gets the style input
        createCarElement(doc, car, "Colour", textColor.getText());//gets the color input
        createCarElement(doc, car, "Mileage", textMileage.getText());//gets the mileage input

        // this function here just wrties the content into the XML file
        //it sets up a tranformer to convert the XML doc into a file 
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);//this is the source for the tranformation  
        StreamResult result = new StreamResult(new File("cars.xml"));//new stream result makes the file cars.xml
        transformer.transform(source, result);//this writes the updated XML doc to the file
        //show user message saying its been made
        JOptionPane.showMessageDialog(this, "XML File Created Successfully!");

    } catch (Exception e) {
        e.printStackTrace();//prints the error stack trace and shows error message
        JOptionPane.showMessageDialog(this, "Error creating XML file.");
    }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
         // validate inputs
    if (!validateInputs()) {
        return; // exit if invalid input
    }
    //checks if file already exists
    try {
        File xmlFile = new File("cars.xml");
        if (!xmlFile.exists()) {//if it doesnt, show user a message saying so 
            JOptionPane.showMessageDialog(this, "No XML file found. Please create one first.");
            return;//xits the method if the file doesnt exist
        }
        //xsets up a factory to basically read and modify the existing XML file 
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFile);//this parses the exisiting XML file 

        // this just normalizes the XML structure. Just a nice feature to add as a bonus
        doc.getDocumentElement().normalize();

        // get the root element. In this case its the inventory
        Element root = doc.getDocumentElement();

        // this creates a new car element
        Element car = doc.createElement("Car");
        root.appendChild(car);//appends the new car element to the root

        // this just will add the elements for the car's details
        createCarElement(doc, car, "Year", textYear.getText());//add year
        createCarElement(doc, car, "Company", textComp.getText());//add company
        createCarElement(doc, car, "Style", textStyle.getText());//add style
        createCarElement(doc, car, "Colour", textColor.getText());//add color
        createCarElement(doc, car, "Mileage", textMileage.getText());//add mileage

        // this writes the updated content back to the XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);//this is the source for the tranformation (updated) 
        StreamResult result = new StreamResult(xmlFile);//this is the target file to update the file 
        transformer.transform(source, result);//this writes the updated XML doc to the file 

        JOptionPane.showMessageDialog(this, "XML File Updated Successfully!");

    } catch (Exception e) {
        e.printStackTrace();//prints the error stack trace and shows an error message if invalid
        JOptionPane.showMessageDialog(this, "Error updating XML file.");
    }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
         // get the search term entered by the user
    String searchTerm = textSearch.getText().trim();

    // If the search term is empty, show user a message 
    if (searchTerm.isEmpty()) {//this checks
        JOptionPane.showMessageDialog(this, "Please enter a search term.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;//exit the method if empty
    }

    try {
        // this loads the XML file
        File xmlFile = new File("cars.xml");
        if (!xmlFile.exists()) {//this just checks if it exists
            //show user an error message saying if its found or not
            JOptionPane.showMessageDialog(this, "No XML file found. Please create one first.");
            return;//exit the method if not found 
        }

        // this parses the XML file
        //makes a document builder factory instance to get a factory to parse the doc
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();//creates new builder to parse
        Document doc = builder.parse(xmlFile);//parses into a whole doc object that represents the entire XML structure
        doc.getDocumentElement().normalize();//normalizes the XML structure to combine similar nodes
                                             //this is good for handling document trees

        // get a list of all car elements in the XML file
        NodeList carList = doc.getElementsByTagName("Car");
        
        // initialize a StringBuilder to store the search results
        StringBuilder results = new StringBuilder();

        // now this loops through each car element and checks if any match the search term
        for (int i = 0; i < carList.getLength(); i++) {
            Element car = (Element) carList.item(i);//checks if it matches

            // Check if the search term matches the Company, Year, or any other field
            //checks if it matches the company
            String company = car.getElementsByTagName("Company").item(0).getTextContent();
            //checks if it matches the year
            String year = car.getElementsByTagName("Year").item(0).getTextContent();
            //checks if it matches the style
            String style = car.getElementsByTagName("Style").item(0).getTextContent();
            //checks if it matches the color
            String color = car.getElementsByTagName("Colour").item(0).getTextContent();
            //checks if it matches the mileage
            String mileage = car.getElementsByTagName("Mileage").item(0).getTextContent();

            // checks if the search term matches any of these values
            if (company.contains(searchTerm) || year.contains(searchTerm) || style.contains(searchTerm) || color.contains(searchTerm)) {
                // If a match is found, append the car details to the results basically
                results.append("Company: ").append(company).append("\n");//append company
                results.append("Year: ").append(year).append("\n");//append year
                results.append("Style: ").append(style).append("\n");//append style
                results.append("Colour: ").append(color).append("\n");//append color
                results.append("Mileage: ").append(mileage).append("\n\n");//append mileage
            }
        }

        // If results are found, display them, otherwise show a message to user saying not found 
        if (results.length() > 0) {//if its found, show it
            searchResultsArea.setText(results.toString());
        } else {
            //show user message
            searchResultsArea.setText("No cars found matching the search term.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        //show user error message
        JOptionPane.showMessageDialog(this, "Error searching the XML file.");
    } 
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearAllCarsInXML(); // Call the method to clear cars from XML file
        searchResultsArea.setText("");
        
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYearActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
try {
    // check if the XML file exists and if it's empty
    File xmlFile = new File("cars.xml");

    // if the file does not exist or is empty, show a message and exit
    if (!xmlFile.exists() || xmlFile.length() == 0) {
        //show user an error message
        JOptionPane.showMessageDialog(null, "The cars.xml file is empty or does not exist.");
        return; // exit the method
    }

    // create a document builder factory to parse the XML file
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    // create a new document builder
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    // parse the existing XML file into a DOM document
    Document doc = docBuilder.parse(xmlFile);

    // normalize the document to remove any irrelevant text nodes
    doc.getDocumentElement().normalize();

    // get a list of all the car elements in the XML document basically 
    NodeList carList = doc.getElementsByTagName("Car");

    // check if the file contains any cars
    if (carList.getLength() == 0) {
         // if not, show user and exit
        JOptionPane.showMessageDialog(null, "No cars found in the file to display.");
        return; // exit the method
    }

    // these are just variables to store the newest car and its year
    Element newestCar = null; // variable to store the newest car
    int newestYear = Integer.MIN_VALUE; // variable to store the newest year

    // this will loop through each car element and find the car with the newest year
    for (int i = 0; i < carList.getLength(); i++) {
        // get each car element
        Element car = (Element) carList.item(i);
        // retrieve the "Year" value as a string
        String yearStr = car.getElementsByTagName("Year").item(0).getTextContent();
        // convert the year string to an integer
        int year = Integer.parseInt(yearStr);

        // if the current car's year is newer than the stored newest year, update the newest car
        if (year > newestYear) {
            newestYear = year; // update the newest year
            newestCar = car; // update the newest car
        }
    }

    // to display details of the newest car(s) with the same year
    StringBuilder carDetails = new StringBuilder();
    carDetails.append("Newest Car(s) Details:\n");
    boolean foundSameYear = false; // flag to check if we found any car with the same year

    // now loop through carList again to find all cars with the newest year
    for (int i = 0; i < carList.getLength(); i++) {
        Element car = (Element) carList.item(i);
        //checks for the same year
        String yearStr = car.getElementsByTagName("Year").item(0).getTextContent();
        int year = Integer.parseInt(yearStr);//parses it

        // If the car's year matches the newest year, include it in the details
        if (year == newestYear) {
            foundSameYear = true; // set flag to true
            //show year details
            carDetails.append("Year: ").append(yearStr).append("\n");
            //show company details
            carDetails.append("Company: ").append(car.getElementsByTagName("Company").item(0).getTextContent()).append("\n");
            //show style details
            carDetails.append("Style: ").append(car.getElementsByTagName("Style").item(0).getTextContent()).append("\n");
            //show color details
            carDetails.append("Colour: ").append(car.getElementsByTagName("Colour").item(0).getTextContent()).append("\n");
            //show mileage details
            carDetails.append("Mileage: ").append(car.getElementsByTagName("Mileage").item(0).getTextContent()).append("\n");
            carDetails.append("--------------------\n"); // seperator so easier to read for user
        }
    }

    // set the text in the big text field
    if (foundSameYear) {
        searchResultsArea.setText(carDetails.toString());
    } else {
        // in case there's no newest car, show user error message
        searchResultsArea.setText("No car data available.");
    }

} catch (Exception e) {
    // print the stack trace in case of an error and show an error message to the user
    e.printStackTrace();
    // show user error message
    JOptionPane.showMessageDialog(null, "Error processing the cars.xml file.");
}
    
     
    
    }//GEN-LAST:event_btnYearActionPerformed

    private void btnClearFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFieldsActionPerformed
        // TODO add your handling code here:
         //the following code will just clear the following fields
        textYear.setText(""); //clear year
        textComp.setText(""); //clear company
        textMileage.setText("");//clear mileage
        textColor.setText("");//clear color
        textStyle.setText("");//clear style
        searchResultsArea.setText("");//clear big text field
        textSearch.setText("");//clear search bar
    }//GEN-LAST:event_btnClearFieldsActionPerformed

    private void btnMileageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMileageActionPerformed
// TODO add your handling code here:
try {
    // check if the XML file exists and if it's empty
    File xmlFile = new File("cars.xml");
    
    // if the file does not exist or is empty, show a message and exit
    if (!xmlFile.exists() || xmlFile.length() == 0) {
        // show user error message
        JOptionPane.showMessageDialog(null, "The cars.xml file is empty or does not exist.");
        return; // exit the method
    }

    // create a document builder factory to parse the XML file
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    // create a new document builder
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    // parse the existing XML file into a DOM document
    Document doc = docBuilder.parse(xmlFile);

    // normalize the document to remove any irrelevant text nodes
    doc.getDocumentElement().normalize();

    // get a list of all the car elements in the XML document
    NodeList carList = doc.getElementsByTagName("Car");

    // check if the file contains any cars, if not, inform the user and exit
    if (carList.getLength() == 0) {
        // show user error message
        JOptionPane.showMessageDialog(null, "No cars found in the file to display.");
        return; // exit the method
    }

    // variable to store the maximum mileage value
    int mostMileage = Integer.MIN_VALUE; // starting with the smallest possible integer value

    // string builder to accumulate details of all cars with the most mileage
    StringBuilder carsWithMostMileage = new StringBuilder();

    // loop through each car element and find the car(s) with the most mileage
    for (int i = 0; i < carList.getLength(); i++) {
        // get each car element
        Element car = (Element) carList.item(i);
        // retrieve the "Mileage" value as a string
        String mileageStr = car.getElementsByTagName("Mileage").item(0).getTextContent();
        // convert the mileage string to an integer
        int mileage = Integer.parseInt(mileageStr);

        // if the current car's mileage is greater than the stored most mileage, update the highest mileage and reset the list of cars
        if (mileage > mostMileage) {
            mostMileage = mileage; // update most mileage value
            carsWithMostMileage.setLength(0); // clear previous cars since a new highest mileage is found
            // append the details of this car to the string builder
            carsWithMostMileage.append("Car with Most Mileage Details:\n");
            //append year 
            carsWithMostMileage.append("Year: ").append(car.getElementsByTagName("Year").item(0).getTextContent()).append("\n");
            //append company
            carsWithMostMileage.append("Company: ").append(car.getElementsByTagName("Company").item(0).getTextContent()).append("\n");
            //append style
            carsWithMostMileage.append("Style: ").append(car.getElementsByTagName("Style").item(0).getTextContent()).append("\n");
            //append color
            carsWithMostMileage.append("Colour: ").append(car.getElementsByTagName("Colour").item(0).getTextContent()).append("\n");
            //append mileage
            carsWithMostMileage.append("Mileage: ").append(car.getElementsByTagName("Mileage").item(0).getTextContent()).append("\n\n");
        }
        // if the current car has the same mileage as the mostMileage, append its details to the list
        else if (mileage == mostMileage) {
            // append the details of this car to the string builder
            carsWithMostMileage.append("Car with Most Mileage Details:\n");
            //append year
            carsWithMostMileage.append("Year: ").append(car.getElementsByTagName("Year").item(0).getTextContent()).append("\n");
            //append company
            carsWithMostMileage.append("Company: ").append(car.getElementsByTagName("Company").item(0).getTextContent()).append("\n");
            //append style
            carsWithMostMileage.append("Style: ").append(car.getElementsByTagName("Style").item(0).getTextContent()).append("\n");
            //append color
            carsWithMostMileage.append("Colour: ").append(car.getElementsByTagName("Colour").item(0).getTextContent()).append("\n");
            //append mileage
            carsWithMostMileage.append("Mileage: ").append(car.getElementsByTagName("Mileage").item(0).getTextContent()).append("\n\n");
        }
    }

    // display the details of the car(s) with the most mileage in the big text field
    if (carsWithMostMileage.length() > 0) {
        searchResultsArea.setText(carsWithMostMileage.toString()); // set the result to the text area
    } else {
        // in case there's no car with mileage show user error message
        searchResultsArea.setText("No car data available.");
    }

} catch (Exception e) {
    // print the stack trace in case of an error and show an error message to the user
    e.printStackTrace();
    // show user error message
    JOptionPane.showMessageDialog(null, "Error processing the cars.xml file.");
}
    
        
    }//GEN-LAST:event_btnMileageActionPerformed
   
   private void clearAllCarsInXML() {
    try {
        // create a file object for the cars.xml file
        File xmlFile = new File("cars.xml");

        // check if the XML file exists
        if (!xmlFile.exists()) {
            // show a message if no XML file is found
            JOptionPane.showMessageDialog(this, "No XML file found.");
            return; // exit the method if the file is not found
        }

        // create a document builder factory instance to parse the XML file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // create a document builder from the factory
        DocumentBuilder builder = factory.newDocumentBuilder();
        // parse the XML file and convert it into a Document object
        Document doc = builder.parse(xmlFile);

        // get the root element of the XML file (in this case, "Cars")
        Node root = doc.getDocumentElement();
        // get a list of all child nodes (i.e., the cars) under the root basically
        NodeList cars = root.getChildNodes();

        // loop to remove all child nodes (cars) under the root element
        while (root.hasChildNodes()) {
            // remove the first child node repeatedly until no children remain
            root.removeChild(root.getFirstChild());
        }

        // create a transformer factory instance to save the updated XML structure
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // create a transformer to apply the changes to the XML file
        Transformer transformer = transformerFactory.newTransformer();
        // create a DOM source from the updated document object
        DOMSource source = new DOMSource(doc);
        // create a StreamResult to write the updated document back to the file
        StreamResult result = new StreamResult(xmlFile);
        // perform the transformation and save the changes
        transformer.transform(source, result);

        // show a message to the user indicating that all cars have been removed
        JOptionPane.showMessageDialog(this, "All cars have been removed.");
    } catch (Exception e) {
        // print the stack trace in case of an error
        e.printStackTrace();
        // show a message indicating an error occurred during the process
        JOptionPane.showMessageDialog(this, "Error clearing cars from XML file.");
    }
}

    
//method to search cars
    private void searchCars() {
     try {
        // read the XML file
        File xmlFile = new File("cars.xml");
        // check if the XML file exists
        if (!xmlFile.exists()) {
            // show a message if no XML file is found
            JOptionPane.showMessageDialog(this, "No XML file found. Please create one first.");
            return; // exit the method if the file is not found
        }

        // parse the XML file
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFile);

        // normalize the XML structure for consistency bascially is what its doing 
        doc.getDocumentElement().normalize();

        // get all the car elements from the XML
        NodeList carList = doc.getElementsByTagName("Car");

        // clear previous search results from the display area
        searchResultsArea.setText("");  // clear the search area for new results

        // get the search criteria from user input fields
        String searchYear = textYear.getText();// get year input
        String searchCompany = textComp.getText();// get company input
        String searchStyle = textStyle.getText();// get style input
        String searchColor = textColor.getText();// get color input

        // iterate through the car elements to filter based on the search criteria
        for (int i = 0; i < carList.getLength(); i++) {
            Element car = (Element) carList.item(i); // gets the current car element

            // get the details of the car
            String year = car.getElementsByTagName("Year").item(0).getTextContent();//get year
            String company = car.getElementsByTagName("Company").item(0).getTextContent();//get company
            String style = car.getElementsByTagName("Style").item(0).getTextContent();//get style
            String color = car.getElementsByTagName("Colour").item(0).getTextContent();//get color

            // assume the car matches the search criteria
            boolean matches = true;
            // check if the year matches the search input
            if (!searchYear.isEmpty() && !year.equals(searchYear)) {
                matches = false; // mark as not matching if year doesn't match
            }
            // check if the company matches the search input
            if (!searchCompany.isEmpty() && !company.equalsIgnoreCase(searchCompany)) {
                matches = false; // mark as not matching if company doesn't match
            }
            // check if the style matches the search input
            if (!searchStyle.isEmpty() && !style.equalsIgnoreCase(searchStyle)) {
                matches = false; // mark as not matching if style doesn't match
            }
            // check if the color matches the search input
            if (!searchColor.isEmpty() && !color.equalsIgnoreCase(searchColor)) {
                matches = false; // mark as not matching if color doesn't match
            }

            // if the car matches, append its details to the JTextArea
            if (matches) {
                searchResultsArea.append("Year: " + year + "\n"); // display year
                searchResultsArea.append("Company: " + company + "\n"); // display company
                searchResultsArea.append("Style: " + style + "\n"); // display style
                searchResultsArea.append("Color: " + color + "\n"); // display color
                searchResultsArea.append("-------------------------------\n"); // separator for clarity
            }
        }

        // check if no matches were found
        if (searchResultsArea.getText().isEmpty()) {
            // show a message if no cars match the criteria
            searchResultsArea.setText("No cars match the search criteria.");
        }

    } catch (Exception e) {
        e.printStackTrace(); // print the stack trace for debugging
        // show a message if there is an error reading the XML file
        JOptionPane.showMessageDialog(this, "Error reading XML file.");
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XMLFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XMLFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XMLFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XMLFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XMLFile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearFields;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnMileage;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea searchResultsArea;
    private javax.swing.JTextField textColor;
    private javax.swing.JTextField textComp;
    private javax.swing.JTextField textMileage;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textStyle;
    private javax.swing.JTextField textYear;
    // End of variables declaration//GEN-END:variables
}
