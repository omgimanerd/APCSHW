import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

  private Container pane_;
  private JButton button_;
  private JLabel label_;

  public Window() {
    this.setTitle("GUI");
    this.setSize(500, 400);
    this.setLocation(100, 100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.pane_ = this.getContentPane();
    this.pane_.setLayout(new FlowLayout());

    this.label_ = new JLabel("Label", null, JLabel.CENTER);
    this.button_ = new Button("
  }

  public static void main(String[] args) {
    Window g = new Window();
    g.setVisible(true);
  }
}

