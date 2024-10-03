/*
 * AffineguiView.java
 */

package affinegui;


import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.Icon;
import javax.swing.JDialog;
import java.text.DecimalFormat;



public class AffineguiView extends FrameView {

    
	
	public AffineguiView(SingleFrameApplication app) {
        super(app);
		initComponents();
        ResourceMap resourceMap = getResourceMap();
    }

  
	 //Codage avec les parametres A et B
     public static String encode(String str2crypt, int a, int b)
	{
		str2crypt=str2crypt.replace(" ","");
		String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String affstr="";
		
		try
		{
		
		for(int i=0;i<str2crypt.length();i++)
			{
			if((i+1)%4!=0)
			affstr=affstr+alphab.substring((((alphab.indexOf(str2crypt.toUpperCase().charAt(i))*a)+b)%26),(((alphab.indexOf(str2crypt.toUpperCase().charAt(i))*a)+b)%26)+1);
			else
			affstr=affstr+alphab.substring((((alphab.indexOf(str2crypt.toUpperCase().charAt(i))*a)+b)%26),(((alphab.indexOf(str2crypt.toUpperCase().charAt(i))*a)+b)%26)+1)+" ";
			}
		}
		catch (Exception e)
		{
			throw new Error("Error.");
		}

		
		return affstr;
	}
	
	
	//Decodage avec les parametres A et B
	public static String decode(String str2decrypt, int a, int b)
	{
	String affstr1="";
	String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	str2decrypt=str2decrypt.replace(" ","");
	
	int invA=0;
		for(int j=1;j<27;j++)
		{
			if((a*j)%26==1)
			{
			invA=j;
			break;
			}
		}
	
	try
		{
		
			for(int m=0;m<str2decrypt.length();m++)
			{
				if(((alphab.indexOf(str2decrypt.charAt(m))-b)*invA)>=0)
				{
				affstr1=affstr1+alphab.substring(((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%26),((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%26)+1);
				}
				else
				{
				affstr1=affstr1+alphab.substring(((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%26)+26,((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%26)+27);
				}
			}
		}
		catch(Exception e1)
		{
		e1.printStackTrace();
		}
	
	return affstr1;
	}
	
	
	//Decodage des 312 cas possibles
	public static String decodebulk(String str2decrypt2)
	{
	String affstr2="";
	int a[]={(1),(3),(5),(7),(9),(11),(15),(17),(19),(21),(23),(25)};
	int count=1;
	
	for(int k=0;k<26;k++)
		{
		for(int l=0;l<12;l++)
		{
		affstr2=affstr2+"count="+count+"\r\na= "+a[l]+" b= "+k+"\r\n";
		affstr2=affstr2+"String Decrypted ...... " + decode(str2decrypt2,a[l],k)+"\r\n\r\n";
		count+=1;
		}
		}
	return affstr2;
	}
        
		
	//Analyse Frequentielle du texte
	public String anfreq(String str)
	{
	String result="\r\nAnalyse Frequentielle\r\n";
	result=result+"----------\r\n";
	String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int[] count = new int[26];
	DecimalFormat df = new DecimalFormat("0.00");
	int totcount=0;
	double total=0;
	try
	{
		for(int i=0;i<str.length();i++)
		{
			for(int j=0;j<26;j++)
			{
				if(j==alphab.indexOf(str.toUpperCase().charAt(i)))
				{
				count[j]+=1;
				}
			}
		}
	
		for(int l=0;l<26;l++)
		{
		totcount=totcount+count[l];
		}
	
		for(int k=0;k<26;k++)
		{
			if(count[k]!=0)
			{
			result=result+alphab.substring(k,k+1)+":"+count[k]+":"+df.format((double)count[k]/(totcount))+"\r\n";
			total=total+((double)count[k]/(totcount));
			}
		}
	result=result+"----------\r\n";
	result=result+"Total: "+df.format(total)+"\r\n";
	result=result+"----------\r\n";
	result=result+"----------\r\n";
	result=result+"Total Lettres: "+totcount+"\r\n";
	result=result+"----------\r\n";
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return result;
	}
   
    private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    
	jComboBox1 = new javax.swing.JComboBox();
    jComboBox2 = new javax.swing.JComboBox();
    
	jTextArea1 = new javax.swing.JTextArea();
    jTextArea2 = new javax.swing.JTextArea();
	jTextArea3 = new javax.swing.JTextArea();
	jTextArea4 = new javax.swing.JTextArea();
    
	jScrollPane1 = new javax.swing.JScrollPane();
    jScrollPane2 = new javax.swing.JScrollPane();
	jScrollPane3 = new javax.swing.JScrollPane();
	jScrollPane4 = new javax.swing.JScrollPane();
    
	jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
   
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(affinegui.AffineguiApp.class).getContext().getResourceMap(AffineguiView.class);
    mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); 
    mainPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    mainPanel.setToolTipText(resourceMap.getString("mainPanel.toolTipText")); 
    mainPanel.setName("mainPanel"); 

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "3", "5", "7", "9", "11", "15", "17", "19", "21", "23", "25" }));
    jComboBox1.setName("jComboBox1");

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));
    jComboBox2.setName("jComboBox2"); 
	jScrollPane2.setName("jScrollPane2"); 

    jTextArea2.setColumns(20);
    jTextArea2.setLineWrap(true);
    jTextArea2.setRows(5);
    jTextArea2.setText(resourceMap.getString("jTextArea2.text")); 
    jTextArea2.setName("jTextArea2"); 
    jScrollPane2.setViewportView(jTextArea2);

    jScrollPane1.setName("jScrollPane1"); 

    jTextArea1.setColumns(20);
    jTextArea1.setLineWrap(true);
    jTextArea1.setRows(5);
    jTextArea1.setText(resourceMap.getString("jTextArea1.text")); 
    jTextArea1.setName("jTextArea1"); 
    jScrollPane1.setViewportView(jTextArea1);

    jLabel1.setText(resourceMap.getString("jLabel1.text")); 
    jLabel1.setName("jLabel1"); 

	jLabel2.setText(resourceMap.getString("jLabel2.text")); 
    jLabel2.setName("jLabel2"); 

    jScrollPane3.setName("jScrollPane3"); 

    jTextArea3.setColumns(20);
    jTextArea3.setLineWrap(true);
    jTextArea3.setRows(5);
    jTextArea3.setName("jTextArea3"); 
    jScrollPane3.setViewportView(jTextArea3);

    jButton1.setText(resourceMap.getString("jButton1.text"));
    jButton1.setName("jButton1"); 
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
        }
    });

    jButton2.setText(resourceMap.getString("jButton2.text")); 
    jButton2.setName("jButton2"); 
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
			jButton2ActionPerformed(evt);
        }
    });

    jButton3.setText(resourceMap.getString("jButton3.text")); 
    jButton3.setName("jButton3"); 
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
			jButton3ActionPerformed(evt);
        }
    });

    jScrollPane4.setName("jScrollPane4"); 

    jTextArea4.setColumns(20);
    jTextArea4.setRows(5);
    jTextArea4.setName("jTextArea4"); 
    jScrollPane4.setViewportView(jTextArea4);

    jButton4.setText(resourceMap.getString("jButton4.text")); 
    jButton4.setName("jButton4"); 
    jButton4.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			jButton4ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
      mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1))
                                .addGap(44, 44, 44)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(38, 38, 38)))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(91, 91, 91)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(93, 93, 93))))
        );


        setComponent(mainPanel);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextArea2.setText("");
        jTextArea2.setText(encode(jTextArea1.getText(),Integer.parseInt(jComboBox1.getSelectedItem().toString()),Integer.parseInt(jComboBox2.getSelectedItem().toString())));
    }

	
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextArea1.setText("");
        jTextArea1.setText(decode(jTextArea2.getText(),Integer.parseInt(jComboBox1.getSelectedItem().toString()),Integer.parseInt(jComboBox2.getSelectedItem().toString())));
        
    }

	
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextArea3.setText("");
        jTextArea3.setText(decodebulk(jTextArea2.getText()));
       
    }

	
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextArea4.setText(anfreq(jTextArea2.getText()));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration
}
