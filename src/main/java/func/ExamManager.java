package func;

import entity.Exam;
import utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExamManager {
    private List<Exam> exams;
    private StudentManager studentManager;
    private String filePath = "src/main/resources/exams.xml";

    public ExamManager() {
        studentManager = new StudentManager();
        this.exams = new ArrayList<>();
        loadExamsFromXML(filePath);
    }

    public List<Exam> searchByDate(String date) {
        String cleanedDate = date.replace("-", "");
        List<Exam> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("^" + cleanedDate.toLowerCase());
        for (Exam exam : exams) {
            Matcher matcher = pattern.matcher(exam.getDate().replace("-", ""));
            if (matcher.find()) {
                results.add(exam);
            }
        }
        return results;
    }

    // Thống kê số lượng xe tối đa của mỗi loại bằng
    public Map<String, Integer> statisticVehicleCountByLicenseType() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Exam exam : exams) {
            String licenseType = exam.getLicenseType();
            int vehicleCount = exam.getVehicleCount();
            countMap.put(licenseType, countMap.getOrDefault(licenseType, 0) + vehicleCount);
        }

        System.out.println("Thống kê số lượng xe theo loại bằng:");
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return countMap;
    }

    private void loadExamsFromXML(String filePath) {
        Document doc = XMLUtils.loadXML(filePath);
        NodeList nodeList = doc.getElementsByTagName("exam");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String date = element.getElementsByTagName("date").item(0).getTextContent();
            String location = element.getElementsByTagName("location").item(0).getTextContent();
            int vehicleCount = Integer.parseInt(element.getElementsByTagName("vehicleCount").item(0).getTextContent());
            String licenseType = element.getElementsByTagName("licenseType").item(0).getTextContent();
            String examForm = element.getElementsByTagName("examForm").item(0).getTextContent();

            exams.add(new Exam(date, location, vehicleCount, licenseType, examForm));
        }
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void addExam(Exam newExam) {
        boolean found = false;

        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);

            if (exam.getDate().equals(newExam.getDate())
                    && exam.getExamForm().equals(newExam.getExamForm())
                    && exam.getLocation().equals(newExam.getLocation())
                    && exam.getLicenseType().equals(newExam.getLicenseType())) {

                int updatedVehicleCount = exam.getVehicleCount() + newExam.getVehicleCount();
                exam.setVehicleCount(updatedVehicleCount);
                exams.set(i, exam);
                found = true;
                break;
            }
        }

        if (!found) {
            exams.add(newExam);
        }

        saveExams();
    }


    public void updateExam(int index, Exam exam) {
        exams.set(index, exam);
        saveExams();
    }

    public void deleteExam(int index) {
        exams.remove(index);
        saveExams();
    }

    private void saveExams() {
        try {
            Document doc = XMLUtils.loadXML(filePath);
            Element root = doc.getDocumentElement();

            // Xóa tất cả các phần tử con
            while (root.hasChildNodes()) {
                root.removeChild(root.getFirstChild());
            }

            for (Exam exam : exams) {
                Element examElement = doc.createElement("exam");

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(exam.getDate()));
                examElement.appendChild(date);

                Element location = doc.createElement("location");
                location.appendChild(doc.createTextNode(exam.getLocation()));
                examElement.appendChild(location);

                Element vehicleCount = doc.createElement("vehicleCount");
                vehicleCount.appendChild(doc.createTextNode(String.valueOf(exam.getVehicleCount())));
                examElement.appendChild(vehicleCount);

                Element licenseType = doc.createElement("licenseType");
                licenseType.appendChild(doc.createTextNode(exam.getLicenseType()));
                examElement.appendChild(licenseType);

                Element examForm = doc.createElement("examForm");
                examForm.appendChild(doc.createTextNode(exam.getExamForm()));
                examElement.appendChild(examForm);

                root.appendChild(examElement);
            }

            XMLUtils.saveXML(filePath, doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Exam> searchByDateAndLicenseType(String date, String licenseType) {
        List<Exam> results = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.getDate().equals(date) && exam.getLicenseType().equals(licenseType)) {
                results.add(exam);
            }
        }
        return results;
    }

}
