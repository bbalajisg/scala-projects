package com.bala.scala.activoids.gui

import scala.swing._;
import com.bala.scala.activoids.gui.util._;

object MainFrame extends swing.SimpleSwingApplication {
 
   
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

	   }
	   frame
	}
}