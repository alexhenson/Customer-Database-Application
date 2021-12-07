package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Appointment;
import model.Contact;
import tools.ButtonEvent;
import tools.StaticObservableLists;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/** This class is responsible for the functionality of the "Reports" controller. */
public class ReportsCtrl implements Initializable {
    @FXML
    private TextArea textArea;

    /** This method activates when the scene starts.
     *  @param url for initialization
     *  @param resourceBundle for initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    /** This method will take you to the Customers Form.
     *  @param actionEvent object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    public void onActionCust(ActionEvent actionEvent) throws IOException {
        System.out.println("Customers button clicked!");
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", actionEvent);
    }

    /** This method will take you to the Main Menu Form.
     *  @param actionEvent object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    public void onActionMainMenu(ActionEvent actionEvent) throws IOException {
        System.out.println("Main Menu button clicked!");
        ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", actionEvent);
    }

    /** This method will take you to the Appointment Form.
     *  @param actionEvent object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    public void onActionAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointments button clicked!");
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", actionEvent);
    }

    /** This method calculates the information necessary for the Customer Appointment Report.
     *  Additionally, at the end of the method it uses a forEach method and a LAMBDA expression to
     *  print out all of the objects in the appointmentList.  It is advantageous to use a LAMBDA here
     *  because we can print the entire ObservableList without actually writing a for each loop to
     *  do it.  We have saved some lines of code doing it this way.
     */
    public void onActionCustApptRBtn() {
        System.out.println("Customer Radio button selected.");
        textArea.clear();
        int countPlanning = 0;
        int countDebrief = 0;
        int countFinancial = 0;
        int countBrainstorm = 0;
        int countCareer = 0;
        int[] countByMonth = new int[12];
        EnumSet<Month> months = EnumSet.allOf(Month.class);

        for (Appointment a : StaticObservableLists.appointmentList) {
            switch (a.getType()) {
                case "Planning Session":
                    countPlanning++;
                    break;
                case "De-Briefing":
                    countDebrief++;
                    break;
                case "Financial Advisory":
                    countFinancial++;
                    break;
                case "Brainstorming Session":
                    countBrainstorm++;
                    break;
                case "Career Planning":
                    countCareer++;
                    break;
            }

            int month = a.getStart().getMonthValue();
            countByMonth[month - 1]++;
        }
        StringBuilder custStr = new StringBuilder("Customer Appointment Counts by Meeting Type: \n" +
                "Planning Session: " + countPlanning + "\n" +
                "De-Briefing: " + countDebrief + "\n" +
                "Financial Advisory: " + countFinancial + "\n" +
                "Brainstorming Session: " + countBrainstorm + "\n" +
                "Career Planning: " + countCareer + "\n\n");

        custStr.append("Customer Appointment Counts by Month:\n");

        int monthInt = 0;
        for (Month m : months) {
            String monthName = m.getDisplayName(TextStyle.FULL , Locale.US);
            custStr.append(monthName).append(": ").append(countByMonth[monthInt]).append("\n");
            monthInt++;
        }
        textArea.setText(custStr.toString());

        //LAMBDA EXPRESSION #1
        System.out.println("All Appointments:");
        StaticObservableLists.appointmentList.forEach(System.out::println);
    }

    /** This method calculates the information necessary for the Contact Appointment Report.
     *  At the end of the method I used two more LAMBDA expressions, I converted the ObservableList
     *  to an array, the array into a stream, and then sorted the stream with a Comparator object
     *  using the LAMBDA expression 'Contact::getEmail', I used another LAMBDA expression,
     *  'c + "'s Email: " + c.getEmail()' to print out the stream wiht a for each method.
     *  The first LAMBDA expression was useful to use the Comparator method 'comparing' in a very concise
     *  and easy-to-read way.
     */
    public void onActionContactRBtn() {
        System.out.println("Contact Radio button selected.");
        textArea.clear();

        StringBuilder contactStr = new StringBuilder();
        for (Contact c : StaticObservableLists.contactList) {
            contactStr.append("Contact Name - ").append(c).append(":\n");
            for (Appointment a : StaticObservableLists.appointmentList) {
                if (a.getContact().equals(c.getContactName())) {
                    contactStr.append(a).append("\n");
                }
            }
            contactStr.append("\n");
        }
        textArea.setText(contactStr.toString());

        //LAMBDA EXPRESSION #2
        int sizeOfContact = StaticObservableLists.contactList.size();
        Contact[] contactArr = new Contact[sizeOfContact];
        for(int i = 0; i < sizeOfContact; i++) {
            contactArr[i] = StaticObservableLists.contactList.get(i);
        }
        Stream<Contact> contactStream = Stream.of(contactArr);
        Stream<Contact> sortedContactEmail = contactStream.sorted(Comparator.comparing(Contact::getEmail));
        sortedContactEmail.forEach((c) -> System.out.println(c + "'s Email: " + c.getEmail()));
    }

    /** This method calculates the information necessary for the Appointment Day Report
     *  This report shows the count of the Appointments categorized by the day of the week.
     */
    public void onActionDayRBtn() {
        System.out.println("Day Radio button selected.");
        textArea.clear();

        StringBuilder dayStr = new StringBuilder();

        EnumSet<DayOfWeek> dows = EnumSet.allOf(DayOfWeek.class);
        dayStr.append("Appointment Counts by Days of the Week:\n");
        for(DayOfWeek dow : dows) {
            String dowName = dow.getDisplayName(TextStyle.FULL , Locale.US);
            int apptCount = 0;
            for (Appointment a : StaticObservableLists.appointmentList) {
                if (a.getStart().getDayOfWeek().equals(dow)) {
                    apptCount++;
                }
            }
            dayStr.append(dowName).append(": ").append(apptCount).append("\n");
        }
        textArea.setText(dayStr.toString());
    }
}
