import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretSanta {
    private List<Participant> participants = new ArrayList<>();

    private void buildEntryWindow(){
        JFrame entryWindow = new JFrame("Enter Participants");
        JPanel entryPanel = new JPanel();

        JTextField entryField = new JTextField("Enter Participant Name...");
        entryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Participant newParticipant = new Participant(entryField.getText());
                if (!participants.contains(newParticipant)){
                    participants.add(new Participant(entryField.getText()));
                    entryField.setText("");
                } else {
                    JOptionPane.showMessageDialog(entryWindow, "Participant already exists!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton doneEntry = new JButton("Done");
        doneEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryWindow.dispose();
                matchPartipcants();
                buildMatchWindow();
            }
        });

        entryPanel.add(entryField);
        entryPanel.add(doneEntry);
        entryWindow.add(entryPanel, BorderLayout.CENTER);
        entryWindow.pack();
        entryWindow.setVisible(true);
    }

    private void buildMatchWindow(){
        JFrame matchWindow = new JFrame("Matches");
        JPanel matchPanel = new JPanel();

        JTextArea matchList = new JTextArea();
        matchList.setEditable(false);

        for (Participant participant : participants){
            matchList.append(participant + "\n");
        }

        matchPanel.add(matchList);
        matchWindow.add(matchPanel);
        matchWindow.pack();
        matchWindow.setVisible(true);
    }

    private void matchPartipcants(){
        Collections.shuffle(participants);
        for (Participant participant : participants){
            int index = participants.indexOf(participant);
            if (index == participants.size() - 1){
                Participant recipient = participants.get(0);
                participant.setRecipient(recipient);
            } else {
                participant.setRecipient(participants.get(index + 1));
            }
        }
    }

    public static void main (String[] args){
        SecretSanta santa = new SecretSanta();
        santa.buildEntryWindow();
    }
}
