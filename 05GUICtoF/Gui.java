import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {
  
  private Container parent_;
  private Container userInput_;
  private Container output_;

  private JTextField inputTemp_;
  private JButton ctof_;
  private JButton ftoc_;
  private JLabel display_;

  public Gui() {
    this.setTitle("Fahrenheit to Celsius Converter");
    this.setSize(800, 100);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.parent_ = this.getContentPane();
    this.parent_.setLayout(new GridLayout(2, 1));
    this.userInput_ = new Container();
    this.userInput_.setLayout(new FlowLayout());
    this.output_ = new Container();
    this.output_.setLayout(new FlowLayout());
    this.parent_.add(this.userInput_);
    this.parent_.add(this.output_);
    
    this.inputTemp_ = new JTextField(20);
    this.ctof_ = new JButton("Centigrade to Fahrenheit");
    this.ctof_.addActionListener(this);
    this.ctof_.setActionCommand("ctof");
    this.ftoc_ = new JButton("Fahrenheit to Centigrade");
    this.ftoc_.addActionListener(this);
    this.ftoc_.setActionCommand("ftoc");
    this.userInput_.add(this.inputTemp_);
    this.userInput_.add(this.ctof_);
    this.userInput_.add(this.ftoc_);
    
    this.display_ = new JLabel();
    this.output_.add(this.display_);
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "ctof":
        try {
          double conversion = Double.parseDouble(this.inputTemp_.getText());
          this.display_.setText("" + ctof(conversion));
        } catch (Exception q) {
          this.display_.setText("Invalid input"); 
        }
        break;
      case "ftoc":
        try {
          double conversion = Double.parseDouble(this.inputTemp_.getText());
          this.display_.setText("" + ftoc(conversion));
        } catch (Exception q) {
          this.display_.setText("Invalid input"); 
        }
        break;
    }
  }
  
  private double ftoc (double f) {
    return (f - 32) * (5.0/9.0);
  }
  
  private double ctof (double c) {
    return (c * (9.0/5.0)) + 32;
  }
  
  public static void main(String[] args) {
    Gui g = new Gui();
    g.setVisible(true);
  }
}

