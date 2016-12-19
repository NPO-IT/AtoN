import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DmitriX on 15.11.2016.
 */
public class GUI extends JFrame {
    private int inf;
    private StringBuilder adrStr;
    private ArrayList<JLabel> adrElemListLb=new  ArrayList<JLabel>();
    private ArrayList<JTextField> adrElemListTf=new  ArrayList<JTextField>();

    protected static JTextArea jTarea;
    protected static JLabel freqJl=new JLabel();

    public void setInf(int inf) {
        this.inf = inf;
    }




    GUI(){
        setTitle("AntoN");
        //завершили при закрытии
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        //позиционирование окна в центре
        //setLocationRelativeTo(null);
        //take screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //позиционируем в центре
        setBounds(screenWidth/2-(screenWidth/8), screenHeight/2-(screenWidth/8),600,600);
        //установим позиционирование в окне в столбец
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JLabel jLabel=new JLabel("Выберите информативность:");
        jLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(jLabel);
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.X_AXIS));
        ButtonGroup bGr=new ButtonGroup();
        JRadioButton[] jRbArr=new JRadioButton[5];
        Boolean bool=true;
        for (int i=0,k=16;i<jRbArr.length;i++,k/=2){
            jRbArr[i]=new JRadioButton("M"+k,bool);
            bGr.add(jRbArr[i]);
            //jRbArr[i].setAlignmentY(CENTER_ALIGNMENT);
            jPanel.add(jRbArr[i]);
            bool=false;
        }
        //jPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        //jPanel.setAlignmentX(CENTER_ALIGNMENT);
        add(jPanel);

        JPanel jp=new JPanel();
        jp.setLayout(new FlowLayout());
        JLabel lbCap=new JLabel("Введите адрес для разбора");
        jp.add(lbCap);
        JLabel lbThread=new JLabel("П");
        JTextField tfThread=new JTextField(1);
        //контролируем ввод только нужного количества символов
        tfThread.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[1-2]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 1) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbThread);
        jp.add(tfThread);
        JLabel lbA=new JLabel("A");
        JTextField tfA=new JTextField(2);
        tfA.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[0-8]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 2) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbA);
        jp.add(tfA);
        JLabel lbB=new JLabel("B");
        JTextField tfB=new JTextField(2);
        tfB.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[0-8]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 2) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbB);
        jp.add(tfB);
        JLabel lbC=new JLabel("C");
        JTextField tfC=new JTextField(2);
        tfC.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[0-8]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 2) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbC);
        jp.add(tfC);
        JLabel lbD=new JLabel("D");
        JTextField tfD=new JTextField(2);
        tfD.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[0-8]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 2) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbD);
        jp.add(tfD);
        JLabel lbE=new JLabel("E");
        JTextField tfE=new JTextField(2);
        tfE.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[0-8]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 2) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbE);
        jp.add(tfE);
        JLabel lbX=new JLabel("X");
        JTextField tfX=new JTextField(2);
        tfX.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null){
                    return;
                }
                //создаем объект регулярного выражения
                Pattern p = Pattern.compile("[0-8]");
                //прогоняем вводимую строку через регулярку
                Matcher m = p.matcher(str);
                //проверяем соответствует ли строка шаблону регулярки
                if (m.matches()){
                    if ((getLength() + str.length()) <= 2) {
                        super.insertString(offset, str, attr);
                    }
                }else{
                    return;
                }
            }
        });
        jp.add(lbX);
        jp.add(tfX);

        //jp.setAlignmentX(CENTER_ALIGNMENT);

        //jp.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        add(jp);

        JButton jButton=new JButton("Выполнить");
        jButton.setAlignmentX(CENTER_ALIGNMENT);
        //установка предпочтительного размера
        jButton.setPreferredSize(new Dimension(150,70));
        add(jButton);


        jButton.setAlignmentX(CENTER_ALIGNMENT);
        add(freqJl);


        //задаем поле отчета в конструкторе передаем его размеры в строках и столбцах
        jTarea=new JTextArea(20,1);
        //add(jTarea);
        //добавление двух скролов
        JScrollPane scrollPane = new JScrollPane(jTarea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);


        adrElemListLb.add(lbThread);
        adrElemListTf.add(tfThread);
        adrElemListLb.add(lbA);
        adrElemListTf.add(tfA);
        adrElemListLb.add(lbB);
        adrElemListTf.add(tfB);
        adrElemListLb.add(lbC);
        adrElemListTf.add(tfC);
        adrElemListLb.add(lbD);
        adrElemListTf.add(tfD);
        adrElemListLb.add(lbE);
        adrElemListTf.add(tfE);
        adrElemListLb.add(lbX);
        adrElemListTf.add(tfX);

        Calcker сulk=new Calcker();

        //добавим обработчик нажатия на кнопку
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //очистка предидущего состояния
                jTarea.setText(null);

                adrStr=new StringBuilder("");
                //получаем информативность
                for (JRadioButton jrb:jRbArr) {
                    if (jrb.isSelected()){
                        adrStr.append(jrb.getText());
                        //возвращаем информативность
                        setInf(Integer.parseInt(jrb.getText().substring(1)));
                        break;
                    }
                }




                Boolean testStringFlag=true;
                for(int i=0;i<adrElemListLb.size();i++){
                    String str=adrElemListTf.get(i).getText();
                    //если информативность М16 то проверяем чтобы поток адреса был указан
                    if ((inf==16)&&(i==0)){
                        if (str.isEmpty()){
                            adrElemListTf.get(i).setText("1");
                        }
                    };

                    //проверим чтобы A был обязательно указан
                    if (i==1){
                        if (str.isEmpty()){
                            testStringFlag=false;
                        }
                    }

                    if (!adrElemListTf.get(i).getText().isEmpty()){
                        adrStr.append(adrElemListLb.get(i).getText());
                        adrStr.append(adrElemListTf.get(i).getText());
                    }
                }

                //проверим строку на правильность
                String str=new  String(adrStr);
                //уберем все цифры, а также все буквы М и П
                str=str.replaceAll("\\d|M|П","");
                for (int i = 0; i <str.length() ; i++) {
                    char a=str.charAt(i);
                    if ((str.charAt(i)=='B')&&(str.charAt(i-1)!='A')){
                        testStringFlag=false;
                        break;
                    };
                    if ((str.charAt(i)=='C')&&(str.charAt(i-1)!='B')){
                        testStringFlag=false;
                        break;
                    };
                    if ((str.charAt(i)=='D')&&(str.charAt(i-1)!='C')){
                        testStringFlag=false;
                        break;
                    };
                    if ((str.charAt(i)=='E')&&(str.charAt(i-1)!='D')){
                        testStringFlag=false;
                        break;
                    };
                    if ((str.charAt(i)=='X')&&(str.charAt(i-1)!='E')){
                        testStringFlag=false;
                        break;
                    };
                }

                if (testStringFlag){
                    //Выводим адрес
                    jTarea.append(new String("Адрес "+adrStr).concat("\n"));
                    jTarea.append(new String("Нумерация слов с 0").concat("\n"));
                    сulk.culk(new String(adrStr));
                    //jTarea.append(new String(adrStr).concat("\n"));
                }else{
                    jTarea.append(new String("Адрес "+adrStr).concat("\n"));
                    jTarea.append("Тфу тфу. Такое есть не буду!");
                }

            }
        });

        // Слушатель события о смене состояния на M16
        jRbArr[0].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jRbArr[0].isSelected()){
                    tfThread.enable();
                };
            }
        });
        jRbArr[1].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jRbArr[1].isSelected()){
                    tfThread.setText("");
                    tfThread.disable();
                };
            }
        });
        jRbArr[2].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jRbArr[2].isSelected()){
                    tfThread.setText("");
                    tfThread.disable();
                };
            }
        });
        jRbArr[3].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jRbArr[3].isSelected()){
                    tfThread.setText("");
                    tfThread.disable();
                };
            }
        });
        jRbArr[4].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jRbArr[4].isSelected()){
                    tfThread.setText("");
                    tfThread.disable();
                };
            }
        });

        //скомпановать по контенту
        pack();

    }
}
