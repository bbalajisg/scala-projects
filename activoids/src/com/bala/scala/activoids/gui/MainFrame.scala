package com.bala.scala.activoids.gui

import scala.swing._;
import com.bala.scala.activoids.gui.util._;
import javax.swing.border.EmptyBorder
import javax.swing.border.LineBorder
import java.awt.Color
	
object MainFrame extends swing.SimpleSwingApplication {
 
   import javax.swing.{JFileChooser, filechooser,ImageIcon}
   
   
  val statusBar = new Label("Status Bar") {
    //preferredSize = new Dimension(250, 100)
    border = new EmptyBorder(20,10,10,10)
   
  }
  
  val statusIcon = new Label() {
    icon = new ImageIcon("resources/imagedata/thumbsUp.png")
     
   
  }
  
  val myHouse = (new scala.swing.Component {
    
    preferredSize = new Dimension(600,600)

    def updateArena = {
      
      peer.repaint(100)
    }
  })
  
	def top = {
	   val frame = new MainFrame{
	   title="Console"
	    
	   menuBar = new MenuBar {
          contents += (new Menu ("File") {
            
            
            contents += (new MenuItem (Action("Open...") {
               Dialog.showMessage(MainFrame.myHouse, CommonMessage.UNDER_CONSTRUNCTION)
            }))
         
            contents += (new MenuItem (Action("Save...") {
                    Dialog.showMessage(MainFrame.myHouse, CommonMessage.UNDER_CONSTRUNCTION)
            }))
            
          })
          
          contents += (new Menu ("Edit") {	
             
             
            contents += (new MenuItem (Action("Cut") {
               Dialog.showMessage(MainFrame.myHouse, CommonMessage.UNDER_CONSTRUNCTION)
            }))
            
            
            contents += (new MenuItem (Action("Copy") {
               Dialog.showMessage(MainFrame.myHouse, CommonMessage.UNDER_CONSTRUNCTION)
            }))
             
            contents += (new MenuItem (Action("Paste") {
               Dialog.showMessage(MainFrame.myHouse, CommonMessage.UNDER_CONSTRUNCTION)
            })) 
            
          })
          
            
          
          contents += (new MenuItem (Action("Help") {
        	    Dialog.showMessage(MainFrame.myHouse, CommonMessage.UNDER_CONSTRUNCTION)
          }))

        }
	   
	   
	   
	   contents = new BoxPanel (Orientation.Horizontal) {
	      
        contents += MainFrame.myHouse
        
        contents += new BoxPanel(Orientation.NoOrientation) {
          
            
          contents += MainFrame.statusBar
          contents += MainFrame.statusIcon
        }
      }
       

	   }
	   MainFrame.myHouse.requestFocus
	   frame
	}
}