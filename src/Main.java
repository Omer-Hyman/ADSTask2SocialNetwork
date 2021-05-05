import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JPanel rootPanel;
    private JTextField userName;
    private JButton LogIn;
    private JList FriendList;
    private JList SuggestedList;
    private JLabel information;
    private JButton BlockFriend;

    private String currentUser;
    private int currentID;
    private SocialNetwork friendsNetwork;

    private String friend;

    public Main() {
        //Load the data
        friendsNetwork = new SocialNetwork();
        friendsNetwork.Load();

        LogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = userName.getText();
                currentID = friendsNetwork.FindUserID(currentUser);
                if (currentID == -1){
                    information.setText("User Information: Unknown");
                }
                else{
                    information.setText("User Information: User Name: "+currentUser+" User ID: "+ currentID);

                    String[] friends = friendsNetwork.GetMyFriends(currentUser);
                    FriendList.setListData(friends);

                    String[] recommendations = friendsNetwork.GetRecommended(currentUser);
                    SuggestedList.setListData(recommendations);
                }
            }
        });

        FriendList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                JList source = (JList) e.getSource();
                if(!e.getValueIsAdjusting() && !FriendList.getSelectionModel().isSelectionEmpty())
                {
                    friend = source.getSelectedValue().toString();
                    BlockFriend.setVisible(true);
                    BlockFriend.setText("Block " + friend);
                }
            }
        });

        BlockFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = userName.getText();
                friendsNetwork.BlockFriend(currentUser, friend);
                String[] friends = friendsNetwork.GetMyFriends(currentUser);
                FriendList.setListData(friends);
                BlockFriend.setVisible(false);
            }
        });

        //Clear the input field
        userName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                userName.setText("");
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
