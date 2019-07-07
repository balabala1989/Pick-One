package pickone;

import java.util.Random;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.util.StringProvider;

public class PicOne extends MainScreen {
    RichTextField rtfHeading;
    private EditField choiceOneEditField;
    private EditField choiceTwoEditField;
   // private Bitmap backgroundBitmap;

    public PicOne() {
    	
        super(NO_VERTICAL_SCROLL);
        
        setTitle( "Pick One" );
        // backgroundBitmap = Bitmap.getBitmapResource("c2.jpg");

        VerticalFieldManager verticalFieldManager = new VerticalFieldManager(VerticalFieldManager.NO_VERTICAL_SCROLL | VerticalFieldManager.NO_VERTICAL_SCROLLBAR){
            
           //Override the paint method to draw the background image.
           /* public void paint(Graphics graphics)
            {
                //Draw the background image and then call paint.
                graphics.drawBitmap(0, 0, 680,840, backgroundBitmap, 0, 0);
                super.paint(graphics);
            }      */   
            
        };
        add(verticalFieldManager);
        Font fontHeading = null;
        
        // Heading saying Pick One
        rtfHeading = new RichTextField("Pick One", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0x00e52f64);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 40, 50);
        try
        {
            FontFamily ff1 = FontFamily.forName("Comic Sans MS");
            fontHeading = ff1.getFont(Font.ITALIC | Font.EXTRA_BOLD , 72);
        }
        catch (Exception e) {
			
        
		}
         
        rtfHeading.setFont(fontHeading);
       
        verticalFieldManager.add( rtfHeading );
       
        choiceOneEditField = new EditField( "Choice 1 : ", "Enter Choice 1", 100, EDITABLE ){
        	protected void onUnfocus() {
        		super.onUnfocus();
        		if(choiceOneEditField.getText().equals(""))
        		{
        			choiceOneEditField.setText("Enter Choice 1");
        		}
        	}
        	
        	protected void onFocus(int direction) {
        		// TODO Auto-generated method stub
        		super.onFocus(direction);
        		if(choiceOneEditField.getText().equals("Enter Choice 1"))
				{
					choiceOneEditField.setText("");
				}
        	}
        	
        };
        choiceOneEditField.setMargin(50,40,40,50);
       
        choiceTwoEditField = new EditField( "Choice 2 : ", "Enter Choice 2", 60, EDITABLE ){
        	protected void onUnfocus() {
        		super.onUnfocus();
        		if(choiceTwoEditField.getText().equals(""))
        		{
        			choiceTwoEditField.setText("Enter Choice 2");
        		}
        	}
        	
        	protected void onFocus(int direction) {
        		// TODO Auto-generated method stub
        		super.onFocus(direction);
        		if(choiceTwoEditField.getText().equals("Enter Choice 2"))
				{
        			choiceTwoEditField.setText("");
				}
        	}
        };
        choiceTwoEditField.setMargin(50,40,40,50);
        
        verticalFieldManager.add(choiceOneEditField);
        verticalFieldManager.add(choiceTwoEditField);
        
        ButtonField buttonField_1 = new ButtonField( "Pick !!!", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER  );
       // buttonField_1.setMargin(50, 160, 50, 0);
        verticalFieldManager.add( buttonField_1 );
        buttonField_1.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
            	sayChoice();
            }
        } );
        
    }

    protected void makeMenu( Menu menu, int instance ) {
    	super.makeMenu(menu, instance);
        MenuItem mntmSayHello = new NewMenuItem();
        menu.add( mntmSayHello );
    }

    private class NewMenuItem extends MenuItem {
        public NewMenuItem() {
            super( new StringProvider( "Pick !!!" ), 0, 0 );
        }

        public void run() {
        	sayChoice();
        }
    }

    private void sayChoice() {
    	
    	String stShowResult = "";
    	
    	if(choiceOneEditField.getText().equals("") || choiceOneEditField.getText().equals("Enter Choice 1"))
    	{
    		Dialog.inform( "Please Enter Choice 1" );
    	}
    	
    	else if(choiceTwoEditField.getText().equals("") || choiceTwoEditField.getText().equals("Enter Choice 2"))
    	{
    		Dialog.inform( "Please Enter Choice 2" );
    	}
    	else
    	{
	    	Random random = new Random();
	    	int choice = random.nextInt(2);
	    	
	    	if(choice == 0)
	    		stShowResult = choiceOneEditField.getText();
	    	else if(choice == 1)
	    		stShowResult = choiceTwoEditField.getText();
	    	else
	    		stShowResult = choiceOneEditField.getText();
	        
	    	Dialog.inform( "Final Choice : \n" + stShowResult);
    	}
    }
   
    protected boolean onSavePrompt() {
        return true;
    }
}
