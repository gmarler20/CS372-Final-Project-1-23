import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import sun.audio.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class GameSimulatorGUI extends JFrame {
    private League NBA = new League();
    private JFrame OpeningFrame;
    private JLayeredPane OpeningPane;
    private JLayeredPane MatchupPane;
    private JLabel MatchupPaneBackground;
    private JLabel OpeningBackground;
    private JLabel OpeningTitle;
    private JLabel LeftLabel;
    private JLabel RightLabel;
    private JButton OpeningStartButton;
    private JButton SimulateButton;
    private JList<String> TeamList1;
    private JList<String> TeamList2;
    private JLabel NBALOGO;
    private JLabel TeamLogo1;
    private JLabel TeamLogo2;
    private JTable StatTable1;
    private JTable StatTable2;

    private JFrame TeamSelectFrame;
    private JPanel VisualPanel;
    private JPanel LeftListPanel;


    GameSimulatorGUI(){
        GenOpeningComponents();
        OpeningFrame.setVisible(true);
    }


    public void GenOpeningComponents(){
        OpeningFrame = new JFrame("NBA Simulator");
        OpeningFrame.setSize(new Dimension(1000,600));
        OpeningFrame.setLayout(new FlowLayout());
        OpeningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        OpeningPane = new JLayeredPane();
        OpeningPane.setPreferredSize(new Dimension(1000,600));
        OpeningFrame.add(OpeningPane);


        OpeningBackground = new JLabel(genImage("openingbackground.png",1000,600));
        OpeningBackground.setBounds(0,0,1000,600);
        OpeningPane.add(OpeningBackground, new Integer(0));

        OpeningTitle = new JLabel("NBA Simulator");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        OpeningTitle.setFont(new Font("Garamond", Font.BOLD, 68));
        OpeningTitle.setBounds(275,100,500,300);
        OpeningTitle.setForeground(Color.white);
        OpeningTitle.setBackground(Color.black);
        //OpeningTitle.setHorizontalAlignment(SwingConstants.CENTER);
        OpeningPane.add(OpeningTitle,new Integer(1));

        OpeningStartButton = new JButton("Start");
        OpeningStartButton.setBounds(450,350,100,50);
        OpeningPane.add(OpeningStartButton, new Integer(1));
        OpeningStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenTeamSelectComponents();
                OpeningFrame.setVisible(false);
                TeamSelectFrame.setVisible(true);
            }
        });
        OpeningPane.revalidate();
        OpeningPane.repaint();
        music();
    }

    public void GenTeamSelectComponents(){
        TeamSelectFrame = new JFrame();
        TeamSelectFrame.setSize(new Dimension(1000,620));
        TeamSelectFrame.setLayout(new FlowLayout());
        TeamSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MatchupPane = new JLayeredPane();
        MatchupPane.setPreferredSize(new Dimension(1000, 620));
        MatchupPaneBackground = new JLabel(genImage("MatchupBackground.jpg", 1000, 620));
        MatchupPaneBackground.setBounds(0,0,1000,620);
        MatchupPane.add(MatchupPaneBackground, new Integer(0));



        ListModel LeftList = new DefaultListModel<>();
        for(int i = 0; i < NBA.getNBATeams().size(); i++) {
            ((DefaultListModel) LeftList).addElement(NBA.NBATeams.get(i).getTeamName());

        }

        TeamList1 = new JList(LeftList);
        TeamList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TeamList1.setLayoutOrientation(JList.VERTICAL);
        TeamList1.setBackground(Color.darkGray);
        TeamList1.setForeground(Color.white);
        TeamList1.setOpaque(true);
        TeamList1.setVisibleRowCount(-1);



        JScrollPane listscroller = new JScrollPane(TeamList1);
        listscroller.setPreferredSize(new Dimension(125, 620));
        listscroller.setBounds(10,0,100,620);
        listscroller.setVisible(true);
     //   listscroller.setBackground(Color.darkGray);
       // listscroller.setOpaque(true);
        MatchupPane.add(listscroller, new Integer(10));


        ListModel RightList = new DefaultListModel<>();
        for(int i = 0; i < NBA.getNBATeams().size(); i++) {
            ((DefaultListModel) RightList).addElement(NBA.NBATeams.get(i).getTeamName());
        }

        TeamList2 = new JList(RightList);
        TeamList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TeamList2.setLayoutOrientation(JList.VERTICAL);
        TeamList2.setBackground(Color.darkGray);
        TeamList2.setForeground(Color.white);
        TeamList2.setOpaque(true);
        TeamList2.setVisibleRowCount(-1);



        JScrollPane listscroller2 = new JScrollPane(TeamList2);
        listscroller2.setPreferredSize(new Dimension(125, 620));
        listscroller2.setBounds(890, 0, 100, 620);
        listscroller2.setVisible(true);
        MatchupPane.add(listscroller2, new Integer(10));

        SimulateButton = new JButton();
        SimulateButton.setText("Simulate");
        SimulateButton.setPreferredSize(new Dimension(125,50));
        SimulateButton.setBounds(450,400,125,50);
        SimulateButton.setVisible(true);
        MatchupPane.add(SimulateButton, new Integer(2));

        NBALOGO = new JLabel(genImage("nba logo.png", 75, 150));
        NBALOGO.setBounds(473, 0, 75, 150);
        MatchupPane.add(NBALOGO, new Integer(3));

        TeamLogo1 = new JLabel(genImage("West All Stars.png", 200,200));
        TeamLogo1.setBounds(192, 140, 200, 200);
        MatchupPane.add(TeamLogo1, new Integer(4));

        TeamLogo2 = new JLabel(genImage("East All Stars.png", 220, 220));
        TeamLogo2.setBounds(604, 144, 220,220);
        MatchupPane.add(TeamLogo2, new Integer(4));

        StatTable1 = new JTable(NBA.NBATeams.get(30).genStats(),NBA.NBATeams.get(30).genColNames());
        StatTable1.setBounds(180,365,400,250);
        StatTable1.setOpaque(false);
        StatTable1.setForeground(Color.white);
        StatTable1.setRowHeight(25);
        StatTable1.setRowMargin(5);
        ((DefaultTableCellRenderer)StatTable1.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        StatTable1.setShowGrid(false);
        MatchupPane.add(StatTable1, new Integer(5));

        StatTable2 = new JTable(NBA.NBATeams.get(31).genStats(),NBA.NBATeams.get(31).genColNames());
        StatTable2.setBounds(605,365,400,250);
        StatTable2.setOpaque(false);
        StatTable2.setForeground(Color.white);
        StatTable2.setRowHeight(25);
        StatTable2.setRowMargin(5);
        ((DefaultTableCellRenderer)StatTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        StatTable2.setShowGrid(false);
        MatchupPane.add(StatTable2, new Integer(5));

        TeamSelectFrame.add(MatchupPane);
        TeamSelectFrame.revalidate();
        TeamSelectFrame.repaint();
        addListeners();

    }

    public ImageIcon genImage(String s, int w, int h){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL imgURL = getClass().getResource("/Resources/"+ s);
        Image image = toolkit.getImage(imgURL);
        image = image.getScaledInstance(w,h,Image.SCALE_SMOOTH);
        ImageIcon imageIcon= new ImageIcon(image);
        return imageIcon;

    }

    public void music(){

    }

    public void addListeners() {
        MouseListener mouseListener = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String selectedItem = TeamList1.getSelectedValue();
                for (int i = 0; i < NBA.NBATeams.size(); i++) {
                    if (selectedItem == NBA.NBATeams.get(i).getTeamName()) {
                        TeamLogo1.setIcon(genImage(NBA.NBATeams.get(i).getTeamName() + ".png", 200, 200));
                        StatTable1.setVisible(false);
                        StatTable1 = new JTable(NBA.NBATeams.get(i).genStats(),NBA.NBATeams.get(i).genColNames());
                        StatTable1.setBounds(180,365,400,250);
                        StatTable1.setOpaque(false);
                        StatTable1.setForeground(Color.white);
                        StatTable1.setRowHeight(25);
                        StatTable1.setRowMargin(5);
                        ((DefaultTableCellRenderer)StatTable1.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
                        StatTable1.setShowGrid(false);
                        MatchupPane.add(StatTable1, new Integer(5));
                       StatTable1.setVisible(true);
                        TeamSelectFrame.revalidate();
                        TeamSelectFrame.repaint();
                    }
                }
            }

        };
        TeamList1.addMouseListener(mouseListener);

        MouseListener List2Listener = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param a
             */
            @Override
            public void mouseClicked(MouseEvent a) {
                super.mouseClicked(a);
                String selectedItem = TeamList2.getSelectedValue();
                for (int i = 0; i < NBA.NBATeams.size(); i++) {
                    if (selectedItem == NBA.NBATeams.get(i).getTeamName()) {
                        TeamLogo2.setIcon(genImage(NBA.NBATeams.get(i).getTeamName() + ".png", 200, 200));
                        StatTable2.setVisible(false);
                        StatTable2 = new JTable(NBA.NBATeams.get(i).genStats(),NBA.NBATeams.get(i).genColNames());
                        StatTable2.setBounds(605,365,400,250);
                        StatTable2.setOpaque(false);
                        StatTable2.setForeground(Color.white);
                        StatTable2.setRowHeight(25);
                        StatTable2.setRowMargin(5);
                        ((DefaultTableCellRenderer)StatTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
                        StatTable2.setShowGrid(false);
                        MatchupPane.add(StatTable2, new Integer(5));
                        StatTable2.setVisible(true);
                        TeamSelectFrame.revalidate();
                        TeamSelectFrame.repaint();
                    }
                }
            }

        };
        TeamList2.addMouseListener(List2Listener);
    }
}
