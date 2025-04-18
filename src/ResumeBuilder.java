import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;

public class ResumeBuilder {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Advanced Resume Builder");

        int y = 20;
        int height = 30;

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(20, y, 100, height);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, y, 250, height);
        y += 40;

        JLabel linkedInLabel = new JLabel("LinkedIn URL:");
        linkedInLabel.setBounds(20, y, 100, height);
        JTextField linkedInField = new JTextField();
        linkedInField.setBounds(150, y, 250, height);
        y += 40;

        JLabel githubLabel = new JLabel("GitHub URL:");
        githubLabel.setBounds(20, y, 100, height);
        JTextField githubField = new JTextField();
        githubField.setBounds(150, y, 250, height);
        y += 40;

        JLabel objLabel = new JLabel("Objective:");
        objLabel.setBounds(20, y, 100, height);
        JTextArea objArea = new JTextArea();
        objArea.setBounds(150, y, 250, 50);
        y += 60;

        JLabel eduLabel = new JLabel("Education:");
        eduLabel.setBounds(20, y, 100, height);
        JTextArea eduArea = new JTextArea();
        eduArea.setBounds(150, y, 250, 50);
        y += 60;

        JLabel skillsLabel = new JLabel("Skills:");
        skillsLabel.setBounds(20, y, 100, height);
        JTextArea skillsArea = new JTextArea();
        skillsArea.setBounds(150, y, 250, 50);
        y += 60;

        JLabel internLabel = new JLabel("Internships/Certifications:");
        internLabel.setBounds(20, y, 150, height);
        JTextArea internArea = new JTextArea();
        internArea.setBounds(150, y, 250, 50);
        y += 60;

        JLabel projLabel = new JLabel("Projects:");
        projLabel.setBounds(20, y, 100, height);
        JTextArea projArea = new JTextArea();
        projArea.setBounds(150, y, 250, 50);
        y += 70;

        JButton generateButton = new JButton("Generate Resume PDF");
        generateButton.setBounds(130, y, 200, 40);

        // Action to generate PDF
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String linkedIn = linkedInField.getText().trim();
                String github = githubField.getText().trim();
                String objective = objArea.getText().trim();
                String education = eduArea.getText().trim();
                String skills = skillsArea.getText().trim();
                String internships = internArea.getText().trim();
                String projects = projArea.getText().trim();

                if (name.isEmpty() || objective.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name and Objective are required!");
                    return;
                }

                String fileName = name.replaceAll("\\s+", "_") + "_Resume.pdf";

                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(fileName));
                    document.open();

                    Font headingFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                    Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                    Font textFont = new Font(Font.FontFamily.HELVETICA, 12);

                    // Name (Heading)
                    Paragraph namePara = new Paragraph(name, headingFont);
                    namePara.setAlignment(Element.ALIGN_CENTER);
                    document.add(namePara);

                    // Links
                    Paragraph links = new Paragraph(linkedIn + " | " + github, textFont);
                    links.setAlignment(Element.ALIGN_CENTER);
                    document.add(links);
                    document.add(new Paragraph(" "));

                    // Objective
                    document.add(new Paragraph("OBJECTIVE", sectionFont));
                    document.add(new Paragraph(objective, textFont));
                    document.add(new Paragraph(" "));

                    // Education
                    document.add(new Paragraph("EDUCATION", sectionFont));
                    for (String line : education.split("\\n")) {
                        document.add(new Paragraph("• " + line, textFont));
                    }
                    document.add(new Paragraph(" "));

                    // Skills
                    document.add(new Paragraph("SKILLS", sectionFont));
                    for (String skill : skills.split("\\n")) {
                        document.add(new Paragraph("• " + skill, textFont));
                    }
                    document.add(new Paragraph(" "));

                    // Internships/Certifications
                    document.add(new Paragraph("INTERNSHIPS / CERTIFICATIONS", sectionFont));
                    for (String intern : internships.split("\\n")) {
                        document.add(new Paragraph("• " + intern, textFont));
                    }
                    document.add(new Paragraph(" "));

                    // Projects
                    document.add(new Paragraph("PROJECTS", sectionFont));
                    for (String proj : projects.split("\\n")) {
                        document.add(new Paragraph("• " + proj, textFont));
                    }

                    document.close();
                    JOptionPane.showMessageDialog(frame, "Resume Created: " + fileName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(linkedInLabel);
        frame.add(linkedInField);
        frame.add(githubLabel);
        frame.add(githubField);
        frame.add(objLabel);
        frame.add(objArea);
        frame.add(eduLabel);
        frame.add(eduArea);
        frame.add(skillsLabel);
        frame.add(skillsArea);
        frame.add(internLabel);
        frame.add(internArea);
        frame.add(projLabel);
        frame.add(projArea);
        frame.add(generateButton);

        frame.setSize(450, 650);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
