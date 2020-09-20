package application;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class SampleController implements Initializable {

		@FXML
	    private TextArea tarea;

	    @FXML
	    private Label lab_title;

	    @FXML
	    private Button btn_open;

	    @FXML
	    private Button btn_save;

	    @FXML
	    private Button btn_saveAs;

	    @FXML
	    private Label lab_msg;

	    File openfile=null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		btn_open.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				open();
				
			}
		});
		btn_save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				save();
			}
		});

		btn_saveAs.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		saveAs();
	}
});
	}
	
	void open()
	{
		try {
			
			FileChooser f=new FileChooser();
			
			
			addfilter(f);
			
			setpath(f);
			
			openfile=f.showOpenDialog(Main.mainstage);
		//System.out.println(openfile.getParent());
		
			File temp=new File("temp.txt");
			FileWriter fw=new FileWriter(temp);
			String h=openfile.getParent();
			String encoded = Base64.getEncoder().encodeToString(h.getBytes());
			System.out.println(encoded);
			fw.write(encoded);
			fw.close();
			
		
		
		
		
		
			FileReader fr=new FileReader(openfile);
			
			String data="";
			int n;
			
			while((n=fr.read())!=-1)
			{
				data=data+(char)n;
			}
			fr.close();
			tarea.setText(data);
			
			lab_title.setText(openfile.getName()+"-Notepad");
			
			lab_msg.setText(openfile.getName()+" file opened successfully!!!");
			
			
			 
		       // System.out.println("decoded value is \t" + decoded);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			lab_msg.setText("Retry!!!");
		}
		
	}
	void save()
	{
		if(openfile!=null)
		{
			try {
				FileWriter fw=new FileWriter(openfile);
				fw.write(tarea.getText());
				fw.close();
				lab_msg.setText(openfile.getName()+" saved successfully!!!");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				lab_msg.setText("Retry!!!");
			}
		}
		else
		{
			saveAs();
		}
	}
	void saveAs()
	{
		try {
			FileChooser fc=new FileChooser();
			
			addfilter(fc);
			
			setpath(fc);
			
			openfile=fc.showSaveDialog(Main.mainstage);
			
			File temp=new File("temp.txt");
			FileWriter fww=new FileWriter(temp);
			fww.write(openfile.getParent());
			fww.close();
			
		
		
			
			FileWriter fw=new FileWriter(openfile);
			fw.write(tarea.getText());
			fw.close();
			lab_title.setText(openfile.getName()+"-Notepad");
			lab_msg.setText(openfile.getName()+" saved successfully!!!");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			lab_msg.setText("Retry!!!");
		}
	}
	void addfilter(FileChooser f)
	{
		FileChooser.ExtensionFilter fileweb = new FileChooser.ExtensionFilter("Web pages", "*.java", "*.html", "*.htm", "*.txt");
		f.getExtensionFilters().add(fileweb);
		
		FileChooser.ExtensionFilter filetxt = new FileChooser.ExtensionFilter("TXT Files",  "*.txt");
		f.getExtensionFilters().add(filetxt);
		
		FileChooser.ExtensionFilter filejava = new FileChooser.ExtensionFilter("JAVA Files", "*.java");
		f.getExtensionFilters().add(filejava);
		
		FileChooser.ExtensionFilter filephp = new FileChooser.ExtensionFilter("PHP Files", "*.php");
		f.getExtensionFilters().add(filephp);
		
		FileChooser.ExtensionFilter fileimg = new FileChooser.ExtensionFilter("IMAGE Files", "*.jpg", "*.png", "*.jpeg");
		f.getExtensionFilters().add(fileimg);
		
		FileChooser.ExtensionFilter fileall = new FileChooser.ExtensionFilter("ALL Files", "*");
		f.getExtensionFilters().add(fileall);
	}
	void setpath(FileChooser f)
	{
		try {
		File temp=new File("temp.txt");
		FileReader frr=new FileReader(temp);
		
		String ddata="";
		int nn;
		
		while((nn=frr.read())!=-1)
		{
			ddata=ddata+(char)nn;
		}
		frr.close();
		String decoded = new String(Base64.getDecoder().decode(ddata));
		System.out.println(decoded);
		
		if(ddata!="")
		{
			String currentPath = Paths.get(decoded).toAbsolutePath().normalize().toString();
			f.setInitialDirectory(new File(currentPath));
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			lab_msg.setText("Retry!!!");
		}
	}
	
	
	
}
