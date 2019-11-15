import javax.swing.JTextField;

public class NoEditJTextField extends JTextField{
        //setEditable(false)
        public NoEditJTextField(String text){
                super(text);
                this.setEditable(false);
                this.setHorizontalAlignment(JTextField.CENTER);
        }
        public NoEditJTextField(String text, int width){
                super(text, width);
                this.setEditable(false);
                this.setHorizontalAlignment(JTextField.CENTER);
        }
}
