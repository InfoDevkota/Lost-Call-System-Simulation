import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.*;

import java.util.Scanner;

public class Layout {
        public static boolean nearestIsMakeCall = false;
        public static int NEXT_STOP = 0;
        public static int MAX_Y_OF_CALL_PROGRESS = 0;
        public static int MAX_Y_OF_DELAY = 0;
        public static int LINK_NO = 3;
        public static int LINE_NO = 2 * LINK_NO + 2;
        public static int NO_OF_CALL_IN_PROCESS = 0;
        public static int nearestEndCallLocation = 0;
        public static int nearestEndCallTime = 0;

        static List<NoEditJTextField> listofLineVal = new ArrayList<NoEditJTextField>();
        static List<Integer> listofLineValValue = new ArrayList<Integer>();

        static List<NoEditJTextField> listFromCallProgress = new ArrayList<NoEditJTextField>();
        static List<Integer> listFromCallProgressValue = new ArrayList<Integer>();
        static List<NoEditJTextField> listToCallProgress = new ArrayList<NoEditJTextField>();
        static List<Integer> listToCallProgressValue = new ArrayList<Integer>();
        static List<NoEditJTextField> listLengthCallProgress = new ArrayList<NoEditJTextField>();
        static List<Integer> listLengthCallProgressValue = new ArrayList<Integer>();

        static JButton nextSec;
        static JButton skipToNext;

        static JLabel clockJLabel;
        static int clockTimmer = 0;

        static NoEditJTextField linkMax;
        static int linkMaxNo = LINK_NO;
        static NoEditJTextField linkInUse;
        static int linkInUseNo = 0;

        static NoEditJTextField counterProcess;
        static int counterProcessNo = 0;
        static NoEditJTextField counterComplete;
        static int counterCompleteNo = 0;
        static NoEditJTextField counterBlock;
        static int counterBlockNo = 0;
        static NoEditJTextField counterBusy;
        static int counterBusyNo = 0;

        static JTextField nextCallFrom;
        static int nextCallFromId = 0;
        static JTextField nextCallTo;
        static int nextCallToId = 0;
        static JTextField nextCallLength;
        static int nextCallLengthNo = 0;
        static JTextField nextCallArrivalTime;
        static int nextCallArrivalTimeNo = 0;

        public static void main(String[] args) {
                Scanner input = new Scanner(System.in);
                // System.out.print("Enter No of Line for the Simmulation:- ");
                // int noOfLines = input.nextInt();
                // LINK_NO = noOfLines;
                // LINE_NO = 2 * LINK_NO + 2;
                // linkMaxNo = LINK_NO;
                // System.out.print("Enter Starting Clock Time for the Simmulation:- ");
                // int clockTimeToStart = input.nextInt();
                // clockTimmer = clockTimeToStart;

                JFrame frame = new JFrame("Layout");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel panel = new JPanel();
                GridBagLayout layout = new GridBagLayout();
                panel.setLayout(layout);

                initalize();
                displayInfo(panel);

                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
        }

        public static void initalize(){
                nextSec = new JButton("Next");
                nextSec.addActionListener(new ActionListener()
                {
                        @Override public void actionPerformed(ActionEvent e) {
                                Layout.nextClicked();
                        }
                });

                skipToNext = new JButton("Skip");
                skipToNext.addActionListener(new ActionListener(){
                        @Override public void actionPerformed(ActionEvent e) {
                                Layout.skipClicked();
                        }
                });

                clockJLabel = new JLabel("0");//Timmer
                clockJLabel.setFont (clockJLabel.getFont().deriveFont(32.0f));

                linkMax = new NoEditJTextField("3", 3);
                linkInUse = new NoEditJTextField("0", 3);

                counterProcess = new NoEditJTextField("0", 3);
                counterComplete = new NoEditJTextField("0", 3);
                counterBlock = new NoEditJTextField("0", 3);
                counterBusy = new NoEditJTextField("0", 3);

                nextCallFrom = new JTextField("", 5);
                nextCallTo = new JTextField("", 5);
                nextCallLength = new JTextField("", 5);
                nextCallArrivalTime = new JTextField("", 5);

                for(int i = 0; i < LINE_NO; i++){
                        listofLineValValue.add(0);
                        listofLineVal.add(new NoEditJTextField("", 3));
                }

                for(int i = 0; i < LINK_NO; i++){
                        listFromCallProgressValue.add(0);
                        listToCallProgressValue.add(0);
                        listLengthCallProgressValue.add(0);
                        listFromCallProgress.add(new NoEditJTextField("", 3));
                        listToCallProgress.add(new NoEditJTextField("", 3));
                        listLengthCallProgress.add(new NoEditJTextField("", 3));
                }
        }

        public static void setValue(){
                clockJLabel.setText(clockTimmer+"");
                linkMax.setText(linkMaxNo+"");
                linkInUse.setText(linkInUseNo+"");
                counterProcess.setText(counterProcessNo+"");
                counterComplete.setText(counterCompleteNo+"");
                counterBlock.setText(counterBlockNo+"");
                counterBusy.setText(counterBusyNo+"");

                for(int i = 0; i < LINE_NO; i++){
                        int temp = listofLineValValue.get(i);
                        listofLineVal.get(i).setText(temp+"");
                }

                for(int i = 0; i < LINK_NO; i++){
                        int temp1 = listFromCallProgressValue.get(i);
                        int temp2 = listToCallProgressValue.get(i);
                        int temp3 = listLengthCallProgressValue.get(i);
                        listFromCallProgress.get(i).setText(temp1+"");
                        listToCallProgress.get(i).setText(temp2+"");
                        listLengthCallProgress.get(i).setText(temp3+"");
                }

                nextCallFrom.setText(nextCallFromId+"");
                nextCallTo.setText(nextCallToId+"");
                nextCallLength.setText(nextCallLengthNo+"");
                nextCallArrivalTime.setText(nextCallArrivalTimeNo+"");
        }

        public static void getValue(){
                String from = nextCallFrom.getText();
                String to = nextCallTo.getText();
                String length = nextCallLength.getText();
                String time = nextCallArrivalTime.getText();
                nextCallFromId = Integer.parseInt(from);
                nextCallToId = Integer.parseInt(to);
                nextCallLengthNo = Integer.parseInt(length);
                nextCallArrivalTimeNo = Integer.parseInt(time);
        }

        public static void displayInfo(JPanel panel){
                GridBagConstraints gbc = new GridBagConstraints();

                setValue();

                gbc.gridx = 1;
                gbc.gridy = 1;
                panel.add(new JLabel("Line"), gbc);

                for(int i = 0; i < LINE_NO; i++){
                        listofLineVal.add(new NoEditJTextField(i+1 +"", 3));
                }

                for(int i = 0; i < LINK_NO; i++){
                        listFromCallProgress.add(new NoEditJTextField("", 3));
                        listToCallProgress.add(new NoEditJTextField("", 3));
                        listLengthCallProgress.add(new NoEditJTextField("", 3));
                }

                for(int i = 0; i < LINE_NO; i++){
                        gbc.gridx = 0;
                        gbc.gridy = i + 2;
                        panel.add(new JLabel((i + 1)+" "), gbc);
                }

                for(int i = 1; i <= LINE_NO; i++){
                        gbc.gridx = 1;
                        gbc.gridy = i + 1;
                        panel.add(listofLineVal.get(i-1), gbc);
                }

                gbc.gridx = 2;
                gbc.gridy = 0;
                panel.add(new JLabel("    "), gbc);

                gbc.gridx = 4;
                gbc.gridy = 1;
                panel.add(new JLabel("Link"), gbc);
                gbc.gridx = 3;
                gbc.gridy = 2;
                panel.add(new JLabel("Max"), gbc);
                gbc.gridx = 4;
                gbc.gridy = 2;
                panel.add(linkMax, gbc);
                gbc.gridx = 3;
                gbc.gridy = 3;
                panel.add(new JLabel("In Use"), gbc);
                gbc.gridx = 4;
                gbc.gridy = 3;
                panel.add(linkInUse, gbc);

                gbc.gridx = 4;
                gbc.gridy = 4;
                panel.add(new JLabel("Clock"), gbc);

                gbc.gridx = 4;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                gbc.gridwidth = 2;
                panel.add(clockJLabel, gbc);
                gbc.gridwidth = 1;
                gbc.gridheight = 1;

                //Next call Section
                gbc.gridx = 7;
                gbc.gridy = 1;
                panel.add(new JLabel("From"), gbc);
                gbc.gridx = 7;
                gbc.gridy = 2;
                panel.add(nextCallFrom, gbc);

                gbc.gridx = 11;
                gbc.gridy = 1;
                panel.add(new JLabel("To"), gbc);
                gbc.gridx = 11;
                gbc.gridy = 2;
                panel.add(nextCallTo, gbc);

                gbc.gridx = 13;
                gbc.gridy = 1;
                panel.add(new JLabel("Length"), gbc);
                gbc.gridx = 13;
                gbc.gridy = 2;
                panel.add(nextCallLength, gbc);

                gbc.gridx = 13;
                gbc.gridy = 3;
                panel.add(new JLabel("Arrival Time"), gbc);
                gbc.gridx = 13;
                gbc.gridy = 4;
                panel.add(nextCallArrivalTime, gbc);

                MAX_Y_OF_DELAY = 5;

                //Call in Progress List
                MAX_Y_OF_DELAY ++;
                gbc.gridx = 7;
                gbc.gridy = MAX_Y_OF_DELAY;
                panel.add(new JLabel("From"), gbc);

                for(int i = 0; i < LINK_NO; i++){
                        gbc.gridx = 7;
                        gbc.gridy = i + MAX_Y_OF_DELAY + 1;
                        panel.add(listFromCallProgress.get(i), gbc);
                        MAX_Y_OF_CALL_PROGRESS = i + MAX_Y_OF_DELAY + 1;
                }

                gbc.gridx = 11;
                gbc.gridy = MAX_Y_OF_DELAY;
                panel.add(new JLabel("To"), gbc);

                for(int i = 0; i < LINK_NO; i++){
                        gbc.gridx = 11;
                        gbc.gridy = i + MAX_Y_OF_DELAY + 1;
                        panel.add(listToCallProgress.get(i), gbc);
                }

                gbc.gridx = 13;
                gbc.gridy = MAX_Y_OF_DELAY;
                panel.add(new JLabel("Length"), gbc);

                for(int i = 0; i < LINK_NO; i++){
                        gbc.gridx = 13;
                        gbc.gridy = i + MAX_Y_OF_DELAY + 1;
                        panel.add(listLengthCallProgress.get(i), gbc);
                }

                MAX_Y_OF_CALL_PROGRESS++;
                gbc.gridx = 5;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(new JLabel("Process"), gbc);
                gbc.gridx = 6;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(new JLabel("Completed"), gbc);
                gbc.gridx = 7;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(new JLabel("Blocked"), gbc);
                gbc.gridx = 8;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(new JLabel("Busy"), gbc);

                MAX_Y_OF_CALL_PROGRESS++;
                gbc.gridx = 5;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(counterProcess, gbc);
                gbc.gridx = 6;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(counterComplete, gbc);
                gbc.gridx = 7;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(counterBlock, gbc);
                gbc.gridx = 8;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS;
                panel.add(counterBusy, gbc);

                gbc.gridx = 3;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS + 1;
                gbc.gridwidth = 3;
                panel.add(nextSec, gbc);

                gbc.gridx = 7;
                gbc.gridy = MAX_Y_OF_CALL_PROGRESS + 1;
                gbc.gridwidth = 3;
                panel.add(skipToNext, gbc);
        }

        public static void nextClicked(){
                getValue();
                nextEventTime();
                
                System.out.println("Clicked... \n");
                System.out.println("Next Arrival = "+ nextCallArrivalTimeNo + "\nNext Call End = " + nearestEndCallTime + "\nNext Stop = "+ NEXT_STOP + ".");
                
                if(clockTimmer < NEXT_STOP){
                        clockTimmer++;
                        setValue();
                }
                if(clockTimmer == NEXT_STOP){
                        performAction();
                        setValue();
                }
        }

        public static void skipClicked(){
                System.out.println("Skip Clicked");
                getValue();
                nextEventTime();
                if(NEXT_STOP != 0){
                        if(clockTimmer < NEXT_STOP){
                                clockTimmer = NEXT_STOP;
                                performAction();
                                setValue();
                        }
                }
        }

        public static void performAction(){
                System.out.println("System will take Action");
                if(nearestIsMakeCall){
                        makeCall();
                }
                else{
                        endCall();
                }
        }

        public static void makeCall(){
                counterProcessNo++;
                int caller = nextCallFromId;
                int receiver = nextCallToId;
                int length = nextCallLengthNo;
                boolean isCallerFree = isFree(caller);
                if(!isCallerFree){
                        System.out.println("How can "+ caller +" make call?");
                        return;
                }
                boolean isReceiverFree = isFree(receiver);
                boolean isLineFree = isLineFree();
                if(!isReceiverFree){
                        //increment busy.
                        counterBusyNo++;
                        System.out.println("Reviever is Busy");
                }
                if(isReceiverFree){
                        if(isLineFree){
                                System.out.println("Make call");
                                NO_OF_CALL_IN_PROCESS++;
                                linkInUseNo++;
                                listofLineValValue.set(receiver - 1, 1);
                                listofLineValValue.set(caller - 1, 1);

                                listFromCallProgressValue.set(LINK_NO - NO_OF_CALL_IN_PROCESS, caller);
                                listToCallProgressValue.set(LINK_NO - NO_OF_CALL_IN_PROCESS, receiver);
                                listLengthCallProgressValue.set(LINK_NO - NO_OF_CALL_IN_PROCESS, clockTimmer + length);
                        } else {
                                System.out.println("All Line are Busy");
                                counterBlockNo++;
                        }
                }
                nextCallFromId = 0;
                nextCallToId = 0;
                nextCallLengthNo = 0;
                nextCallArrivalTimeNo = 0;

        }

        public static boolean isFree(int receiver){
                int flag = listofLineValValue.get(receiver - 1);
                if(flag == 1){
                        return false;
                }
                return true;
        }

        public static boolean isLineFree(){
                if(linkInUseNo < linkMaxNo){
                        return true;
                }
                return false;
        }

        public static void endCall(){
                System.out.println("End the call");
                NO_OF_CALL_IN_PROCESS--;
                linkInUseNo--;
                counterCompleteNo++;

                int endingCaller = listFromCallProgressValue.get(nearestEndCallLocation);
                int endingReceiver = listToCallProgressValue.get(nearestEndCallLocation);

                listFromCallProgressValue.set(nearestEndCallLocation, 0);
                listToCallProgressValue.set(nearestEndCallLocation, 0);
                listLengthCallProgressValue.set(nearestEndCallLocation, 0);

                for(int i = 0; i <= NO_OF_CALL_IN_PROCESS; i++){
                        //Shift the call in the call progress table.
                }

                listofLineValValue.set(endingCaller - 1, 0);
                listofLineValValue.set(endingReceiver - 1, 0);

                nearestEndCall();
        }

        public static void nextEventTime(){
                boolean startIsNotZero = false;
                boolean endIsNotZero = false;
                nearestIsMakeCall = true;
                NEXT_STOP = nextCallArrivalTimeNo;
                nearestEndCall();
                if(nearestEndCallTime != 0){
                        endIsNotZero = true;
                        nearestIsMakeCall = false;
                        NEXT_STOP = nearestEndCallTime;
                }
                if(nextCallArrivalTimeNo !=0){
                        startIsNotZero = true;
                        nearestIsMakeCall = true;
                        NEXT_STOP = nextCallArrivalTimeNo;
                }
                if(startIsNotZero && endIsNotZero){
                        if(nearestEndCallTime < nextCallArrivalTimeNo){
                                nearestIsMakeCall = false;
                                NEXT_STOP = nearestEndCallTime;
                        } else {
                                nearestIsMakeCall = true;
                                NEXT_STOP = nextCallArrivalTimeNo;
                        }
                }
        }

        public static void nearestEndCall(){
                int temp = listLengthCallProgressValue.get(LINK_NO - 1);
                int location = LINK_NO - 1;
                for(int i = 0; i<LINK_NO; i++){
                        if(listLengthCallProgressValue.get(i) != 0){
                                temp = listLengthCallProgressValue.get(i);
                                location = i;
                        }
                }
                for(int i = 0; i<LINK_NO; i++){
                        if(temp > listLengthCallProgressValue.get(i) && listLengthCallProgressValue.get(i) != 0){
                                temp = listLengthCallProgressValue.get(i);
                                location = i;
                        }
                }
                nearestEndCallLocation = location;
                nearestEndCallTime = temp;
                System.out.println("Nearest End Call function called.");
        }

}
